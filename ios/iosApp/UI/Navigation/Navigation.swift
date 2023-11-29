import SwiftUI

fileprivate enum AllScreen<Screen> {
    case root
    case screen(Screen)
}

extension View {
    func router<Screen, ScreenView: View>(_ routes: Binding<[Route<Screen>]>, embedInNavigationView: Bool = false, @ViewBuilder buildView: @escaping (Screen, Int) -> ScreenView) -> some View {
        let allScreens = Binding<[Route<AllScreen<Screen>>]>(
            get: {
                let root: Route<AllScreen<Screen>> = .root(AllScreen.root, embedInNavigationView: embedInNavigationView)
                let remainder = routes.wrappedValue.map { route in
                    route.map(AllScreen<Screen>.screen)
                }
                return [root] + remainder
            },
            set: { allScreenRoutes in
                let screenRoutes = allScreenRoutes[1...]
                    .compactMap { (route: Route<AllScreen<Screen>>) -> Route<Screen> in
                        route.map { (allScreen: AllScreen<Screen>) -> Screen in
                            guard case .screen(let screen) = allScreen else {
                                fatalError("Root screen in non-root position. This should not be possible.")
                            }
                            return screen
                        }
                    }
                routes.wrappedValue = screenRoutes
            }
        )
        return Router(routes: allScreens) { allScreen, index in
            switch allScreen {
            case .root:
                self
            case .screen(let screen):
                buildView(screen, index - 1)
            }
        }
    }
}

struct Router<Screen, ScreenView: View>: View {

    @Binding var routes: [Route<Screen>]

    @ViewBuilder var buildView: (Screen, Int) -> ScreenView

    public var body: some View {
        routes
            .enumerated()
            .reversed()
            .reduce(Node<Screen, ScreenView>.end) { nextNode, new in
                let (index, route) = new
                return Node<Screen, ScreenView>.route(
                    route,
                    next: nextNode,
                    allRoutes: $routes,
                    index: index,
                    buildView: { buildView($0, index) }
                )
            }
    }
}

public enum Route<Screen> {
    case sheet(Screen, embedInNavigationView: Bool, onDismiss: (() -> Void)? = nil)
    case cover(Screen, embedInNavigationView: Bool, onDismiss: (() -> Void)? = nil)
    case push(Screen, onDismiss: (() -> Void)? = nil)

    public static func root(_ screen: Screen, embedInNavigationView: Bool) -> Route {
        .sheet(screen, embedInNavigationView: embedInNavigationView)
    }

    public var embedInNavigationView: Bool {
        switch self {
        case .push:
            return false
        case .sheet(_, let embedInNavigationView, _), .cover(_, let embedInNavigationView, _):
            return embedInNavigationView
        }
    }

    public var screen: Screen {
        get {
            switch self {
            case .sheet(let screen, _, _), .cover(let screen, _, _), .push(let screen, _):
                return screen
            }
        }
        set {
            switch self {
            case let .sheet(_, embedInNavigationView, onDismiss):
                self = .sheet(newValue, embedInNavigationView: embedInNavigationView, onDismiss: onDismiss)
            case let .push(_, onDismiss):
                self = .push(newValue, onDismiss: onDismiss)
            case let .cover(_, embedInNavigationView, onDismiss):
                self = .cover(newValue, embedInNavigationView: embedInNavigationView, onDismiss: onDismiss)
            }
        }
    }

    public func map<NewScreen>(_ transform: (Screen) -> NewScreen) -> Route<NewScreen> {
        switch self {
        case let .push(_, onDismiss):
            return .push(transform(screen), onDismiss: onDismiss)
        case let .sheet(_, embedInNavigationView, onDismiss):
            return .sheet(transform(screen), embedInNavigationView: embedInNavigationView, onDismiss: onDismiss)
        case let .cover(_, embedInNavigationView, onDismiss):
            return .cover(transform(screen), embedInNavigationView: embedInNavigationView, onDismiss: onDismiss)
        }
    }
}

// swiftlint:disable enum_case_associated_values_count
indirect enum Node<Screen, V: View>: View {
    case route(Route<Screen>, next: Node<Screen, V>, allRoutes: Binding<[Route<Screen>]>, index: Int, buildView: (Screen) -> V)
    case end

    private var isActiveBinding: Binding<Bool> {
        switch self {
        case .end, .route(_, .end, _, _, _):
            return .constant(false)
        case let .route(_, .route, allRoutes, index, _):
            return Binding(
                get: {
                    if #available(iOS 17.0, *) {
                        if index == 2 {
                            print("isACtive \(allRoutes.wrappedValue.count != index + 1) Count: \(allRoutes.wrappedValue.count) index: \(index)")
                        }
                        return allRoutes.wrappedValue.count != index + 1
                    } else {
                        return allRoutes.wrappedValue.count > index + 1
                    }
                },
                set: { isShowing in
                    guard !isShowing else { return }
                    guard allRoutes.wrappedValue.count != index + 1 else { return }
                    let lastRoute = allRoutes.wrappedValue.last
                    allRoutes.wrappedValue = Array(allRoutes.wrappedValue.prefix(index + 1))
                    if let lastRoute {
                        switch lastRoute {
                        case .push(_, let onDismiss):
                            onDismiss?()
                        default:
                            break
                        }
                    }
                }
            )
        }
    }

    private var sheetBinding: Binding<Bool> {
        switch next {
        case .route(.sheet, _, _, _, _):
            return isActiveBinding
        default:
            return .constant(false)
        }
    }

    private var coverBinding: Binding<Bool> {
        switch next {
        case .route(.cover, _, _, _, _):
            return isActiveBinding
        default:
            return .constant(false)
        }
    }

    private var pushBinding: Binding<Bool> {
        switch next {
        case .route(.push, _, _, _, _):
            return isActiveBinding
        default:
            return .constant(false)
        }
    }

    private var onDismiss: (() -> Void)? {
        switch next {
        case .route(.sheet(_, _, let onDismiss), _, _, _, _), .route(.cover(_, _, let onDismiss), _, _, _, _), .route(.push(_, let onDismiss), _, _, _, _):
            return onDismiss
        default:
            return nil
        }
    }

    private var route: Route<Screen>? {
        switch self {
        case .end:
            return nil
        case .route(let route, _, _, _, _):
            return route
        }
    }

    private var next: Node? {
        switch self {
        case .end:
            return nil
        case .route(_, let next, _, _, _):
            return next
        }
    }

    @ViewBuilder
    private var screenView: some View {
        switch self {
        case .end:
            EmptyView()
        case let .route(route, _, _, _, buildView):
            buildView(route.screen)
        }
    }

    @ViewBuilder
    private var unwrappedBody: some View {
        screenView
            .sheet(
                isPresented: sheetBinding,
                onDismiss: onDismiss,
                content: { next }
            )
            .fullScreenCover(
                isPresented: coverBinding,
                onDismiss: onDismiss,
                content: { next }
            )
            .background(
                NavigationLink(destination: next, isActive: pushBinding, label: EmptyView.init)
                    .hidden()
            )
    }

    var body: some View {
        if route?.embedInNavigationView ?? false {
            NavigationView {
                unwrappedBody
            }
            .navigationViewStyle(.stack)
        } else {
            unwrappedBody
        }
    }
}
