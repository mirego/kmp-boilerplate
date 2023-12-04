import SwiftUI
import Shared

public enum NavigationType<Screen> {
    case root
    case push(screen: Screen, onDismiss: (() -> Void))
    case sheet(screen: Screen, embedInNavigationView: Bool, onDismiss: (() -> Void))
    case fullScreenCover(screen: Screen, embedInNavigationView: Bool, onDismiss: (() -> Void))

    var screen: Screen? {
        switch self {
        case .root:
            return nil
        case .push(let screen, _), .fullScreenCover(let screen, _, _), .sheet(let screen, _, _):
            return screen
        }
    }
}

public extension View {
    func navigation<Screen: VMDNavigationRoute, ScreenView: View>(
        navigationManager: VMDNavigationManager<Screen>,
        @ViewBuilder buildView: @escaping (Screen) -> ScreenView,
        buildNavigation: @escaping ([Screen], Screen) -> NavigationType<Screen>
    ) -> some View {
        modifier(
            NavigationModifier<Screen, ScreenView>(
                buildView: buildView,
                buildNavigation: buildNavigation,
                navigationManager: navigationManager
            )
        )
    }
}

private struct NavigationModifier<Screen: VMDNavigationRoute, ScreenView: View>: ViewModifier {
    @StateObject private var rootState: NavigationState<Screen>

    @ViewBuilder private let buildView: (Screen) -> ScreenView

    init(
        buildView: @escaping (Screen) -> ScreenView,
        buildNavigation: @escaping ([Screen], Screen) -> NavigationType<Screen>,
        navigationManager: VMDNavigationManager<Screen>? = nil
    ) {
        self.buildView = buildView
        let rootNavigationState = NavigationState<Screen>(
            navigation: NavigationType.root,
            buildNavigation: buildNavigation,
            navigationManager: navigationManager
        )
        _rootState = StateObject(wrappedValue: rootNavigationState)
    }

    func body(content: Content) -> some View {
        NavigationContainerView(navigateState: rootState, buildView: buildView) {
            content
        }
    }
}

/*
    Since VMDNavigationManagerListener is a generic Objective-C class we must bind type parameters
    of VMDNavigationManagerListener to specific concrete types (VMDNavigationRoute).
    And this is why we have to cast the route to Screen in push and popTo methods
 */
private class NavigationState<Screen: VMDNavigationRoute>: VMDNavigationManagerListener<VMDNavigationRoute>, ObservableObject {
    let navigation: NavigationType<Screen>

    @Published var child: NavigationState<Screen>?

    private let buildNavigation: (([Screen], Screen) -> NavigationType<Screen>)?
    private let navigationManager: VMDNavigationManager<Screen>?

    private var lastNavigationDate: Date?

    init(
        navigation: NavigationType<Screen>,
        buildNavigation: (([Screen], Screen) -> NavigationType<Screen>)? = nil,
        navigationManager: VMDNavigationManager<Screen>? = nil
    ) {
        self.navigation = navigation
        self.buildNavigation = buildNavigation
        self.navigationManager = navigationManager

        super.init()
        navigationManager?.listener = self as? VMDNavigationManagerListener<Screen>
    }

    override func push(route: VMDNavigationRoute) {
        guard let buildNavigation else { fatalError("buildNavigation not set")}
        guard let route = route as? Screen else { fatalError("Invalid route type")}

        dedounceNavigation { [weak self] in
            guard let self else { return }
            lastNavigationDate = Date()
            top().child = NavigationState(navigation: buildNavigation(currentStack(), route))
        }
    }

    override func popTo(route: VMDNavigationRoute, included: Bool) {
        guard let route = route as? Screen else { fatalError("Invalid route type") }

        dedounceNavigation { [weak self] in
            guard let self else { return }
            if let routeNavigationState = findLast(route: route) {
                if included {
                    findParent(state: routeNavigationState)?.child = nil
                } else {
                    routeNavigationState.child = nil
                }
            }
        }
    }

    override func pop() {
        dedounceNavigation { [weak self] in
            self?.topPresenter()?.child = nil
        }
    }

    private func dedounceNavigation(action: @escaping () -> Void) {
        let navigationAnimationDuration = 0.55

        if let lastNavigationDate {
            let timeIntervalSinceNow = -lastNavigationDate.timeIntervalSinceNow
            if timeIntervalSinceNow < navigationAnimationDuration {
                DispatchQueue.main.asyncAfter(deadline: .now() + (navigationAnimationDuration - timeIntervalSinceNow)) {
                    action()
                }
            } else {
                action()
            }
        } else {
            action()
        }
    }

    private func top() -> NavigationState<Screen> {
        var current = self

        while current.child != nil {
            current = current.child!
        }
        return current
    }

    private func topPresenter() -> NavigationState<Screen>? {
        guard self.child != nil else { return nil }

        var current = self
        while current.child?.child != nil {
            current = current.child!
        }
        return current
    }

    private func findLast(route: Screen) -> NavigationState<Screen>? {
        var current: NavigationState<Screen>? = self
        var result: NavigationState<Screen>?
        repeat {
            if current?.navigation.screen?.uniqueId == route.uniqueId {
                result = current
            }
            current = current?.child
        } while current != nil

        return result
    }

    private func findParent(state: NavigationState<Screen>) -> NavigationState<Screen>? {
        var current = self
        while current.child != nil {
            if state === current.child {
                return current
            }
            current = current.child!
        }
        return nil
    }

    private func currentStack() -> [Screen] {
        var stack: [NavigationType<Screen>] = []
        var current: NavigationState<Screen>? = self
        while current != nil {
            stack.append(current!.navigation)
            current = current?.child
        }
        return stack.compactMap { $0.screen }
    }
}

private struct NavigationContainerView<Screen: VMDNavigationRoute, Content: View, ScreenView: View>: View {
    @ObservedObject var navigateState: NavigationState<Screen>

    @ViewBuilder let buildView: (Screen) -> ScreenView
    let content: () -> Content

    var body: some View {
        if embedInNavigationView {
            NavigationView {
                unwrappedBody
            }
            .navigationViewStyle(.stack)
        } else {
            unwrappedBody
        }
    }

    @ViewBuilder
    private var unwrappedBody: some View {
        content()
            .sheet(
                isPresented: sheetBinding,
                onDismiss: childOnDismiss,
                content: { childView }
            )
            .fullScreenCover(
                isPresented: fullScreenCoverBinding,
                onDismiss: childOnDismiss,
                content: { childView }
            )
            .background(
                NavigationLink(destination: childView, isActive: pushBinding, label: EmptyView.init)
                    .hidden()
            )
    }

    private var embedInNavigationView: Bool {
        switch navigateState.navigation {
        case .root:
            return false
        case .push:
            return false
        case .sheet(_, let embedInNavigationView, _):
            return embedInNavigationView
        case .fullScreenCover(_, let embedInNavigationView, _):
            return embedInNavigationView
        }
    }

    @ViewBuilder
    private var childView: some View {
        if let child = navigateState.child {
            switch child.navigation {
            case .root:
                EmptyView()
            case .push(let screen, _), .fullScreenCover(let screen, _, _), .sheet(let screen, _, _):
                NavigationContainerView<Screen, ScreenView, ScreenView>(navigateState: child, buildView: buildView) {
                    buildView(screen)
                }
            }
        } else {
            EmptyView()
        }
    }

    private var isActiveBinding: Binding<Bool> {
        Binding(
            get: { navigateState.child != nil },
            set: { isShowing in
                if !isShowing {
                    if isChildPush {
                        childOnDismiss?()
                    }
                    navigateState.child = nil
                }
            }
        )
    }

    private var sheetBinding: Binding<Bool> {
        guard let child = navigateState.child else { return .constant(false) }
        switch child.navigation {
        case .sheet:
            return isActiveBinding
        default:
            return .constant(false)
        }
    }

    private var fullScreenCoverBinding: Binding<Bool> {
        guard let child = navigateState.child else { return .constant(false) }
        switch child.navigation {
        case .fullScreenCover:
            return isActiveBinding
        default:
            return .constant(false)
        }
    }

    private var pushBinding: Binding<Bool> {
        guard let child = navigateState.child else { return .constant(false) }
        switch child.navigation {
        case .push:
            return isActiveBinding
        default:
            return .constant(false)
        }
    }

    private var childOnDismiss: (() -> Void)? {
        guard let child = navigateState.child else { return nil }
        switch child.navigation {
        case .root:
            return nil
        case .push(_, let onDismiss):
            return onDismiss
        case .sheet(_, _, let onDismiss):
            return onDismiss
        case .fullScreenCover(_, _, let onDismiss):
            return onDismiss
        }
    }

    private var isChildPush: Bool {
        guard let child = navigateState.child else { return false }
        switch child.navigation {
        case .push:
            return true
        default:
            return false
        }
    }
}
