import SwiftUI
import Shared

enum NavigationType<Screen> {
    case root
    case push(screen: Screen, onDismiss: (() -> Void))
    case sheet(screen: Screen, embedInNavigationView: Bool, onDismiss: (() -> Void))
    case fullScreenCover(screen: Screen, embedInNavigationView: Bool, onDismiss: (() -> Void))
}

extension View {
    func navigation<Screen: VMDNavigationRoute, ScreenView: View, Result: VMDNavigationResult>(
        navigationManager: VMDNavigationManager<Screen, Result>,
        @ViewBuilder buildView: @escaping (Screen) -> ScreenView,
        buildNavigation: @escaping (Screen) -> NavigationType<Screen>
    ) -> some View {
        modifier(
            NavigationModifier<Screen, ScreenView, Result>(
                buildView: buildView,
                buildNavigation: buildNavigation,
                navigationManager: navigationManager
            )
        )
    }
}

private struct NavigationModifier<Screen: VMDNavigationRoute, ScreenView: View, Result: VMDNavigationResult>: ViewModifier {
    @StateObject private var rootState: NavigationState<Screen, Result>

    @ViewBuilder private let buildView: (Screen) -> ScreenView

    init(
        buildView: @escaping (Screen) -> ScreenView,
        buildNavigation: @escaping (Screen) -> NavigationType<Screen>,
        navigationManager: VMDNavigationManager<Screen, Result>? = nil
    ) {
        self.buildView = buildView
        let rootNavigationState = NavigationState<Screen, Result>(
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
private class NavigationState<Screen: VMDNavigationRoute, Result: VMDNavigationResult>: VMDNavigationManagerListener<VMDNavigationRoute>, ObservableObject {
    let navigation: NavigationType<Screen>

    @Published var child: NavigationState<Screen, Result>?

    private let buildNavigation: ((Screen) -> NavigationType<Screen>)?
    private let navigationManager: VMDNavigationManager<Screen, Result>?

    init(
        navigation: NavigationType<Screen>,
        buildNavigation: ((Screen) -> NavigationType<Screen>)? = nil,
        navigationManager: VMDNavigationManager<Screen, Result>? = nil
    ) {
        self.navigation = navigation
        self.buildNavigation = buildNavigation
        self.navigationManager = navigationManager

        super.init()
        navigationManager?.listener = self as? VMDNavigationManagerListener<Screen>
    }

    override func push(route: VMDNavigationRoute) {
        guard let buildNavigation = buildNavigation else { fatalError("buildNavigation not set")}
        guard let route = route as? Screen else { fatalError("Invalid route type")}

        top().child = NavigationState(navigation: buildNavigation(route))
    }

    override func popTo(route: VMDNavigationRoute) {
        // guard let route = route as? Screen else { fatalError("Invalid route type")}
    }

    override func pop() {
        topPresenter()?.child = nil
    }

    private func top() -> NavigationState<Screen, Result> {
        var current = self

        while current.child != nil {
            current = current.child!
        }
        return current
    }

    private func topPresenter() -> NavigationState<Screen, Result>? {
        guard self.child != nil else { return nil }

        var current = self
        while current.child?.child != nil {
            current = current.child!
        }
        return current
    }
}

private struct NavigationContainerView<Screen: VMDNavigationRoute, Content: View, ScreenView: View, Result: VMDNavigationResult>: View {
    @ObservedObject var navigateState: NavigationState<Screen, Result>

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
                NavigationContainerView<Screen, ScreenView, ScreenView, Result>(navigateState: child, buildView: buildView) {
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
