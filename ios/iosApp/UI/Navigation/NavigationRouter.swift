import SwiftUI
import Shared

struct NavigationRouter: ViewModifier {
    @StateObject private var navigationRouterHolder: NavigationRouterHolder

    init(navigationRouter: DemoNavigationManager) {
        _navigationRouterHolder = StateObject(wrappedValue: NavigationRouterHolder(navigationRouter: navigationRouter))
    }

    func body(content: Content) -> some View {
        content
            .router($navigationRouterHolder.routes) { screen, _ in
                switch onEnum(of: screen) {
                case .root:
                    Text("Root !!!")
                case .tab1:
                    Text("Tab 1 !!!")
                case .tab2:
                    Text("Tab 2 !!!")
                case .screen1(let route):
                    Screen1View(viewModel: navigationRouterHolder.navigationRouter.createScreen1(route: route))
                case .screen2(let route):
                    Screen2View(viewModel: navigationRouterHolder.navigationRouter.createScreen2(route: route))
                case .screen3(let route):
                    Screen3View(viewModel: navigationRouterHolder.navigationRouter.createScreen3(route: route))
                case .dialog(let route):
                    Text("Dialog")
                case .externalUrl(let route):
                    Text("ExternalUrl")
                }
            }
    }
}

extension View {
    func navigationRouter(_ navigationRouter: DemoNavigationManager) -> some View {
         modifier(NavigationRouter(navigationRouter: navigationRouter))
    }
}

private class NavigationRouterHolder: VMDNavigationManagerListener<DemoNavigationRoute>, ObservableObject {

    let navigationRouter: DemoNavigationManager

    @Published var routes: [Route<DemoNavigationRoute>] = []

    init(navigationRouter: DemoNavigationManager) {
        self.navigationRouter = navigationRouter
        super.init()

        navigationRouter.listener = self
    }

    override func pop() {
        routes.removeLast()
    }

    override func popTo(route: DemoNavigationRoute) {

    }

    override func push(route: DemoNavigationRoute) {
        let newRoute: Route<DemoNavigationRoute>
        switch onEnum(of: route) {
        case .root:
            fatalError("Can't navigate to root")
        case .tab1:
            fatalError("Can't navigate to tab1")
        case .tab2:
            fatalError("Can't navigate to tab2")
        case .screen1:
            newRoute = .push(route) { [weak self] in
                print("OnDismiss PUSH")
                self?.navigationRouter.popped(route: route)
            }
        case .screen2:
            newRoute = .cover(route, embedInNavigationView: false) { [weak self] in
                print("OnDismiss")
                self?.navigationRouter.popped(route: route)
            }
        case .screen3:
            newRoute = .sheet(route, embedInNavigationView: true) { [weak self] in
                print("OnDismiss")
                self?.navigationRouter.popped(route: route)
            }
        case .dialog:
            newRoute = .sheet(route, embedInNavigationView: false) { [weak self] in
                print("OnDismiss")
                self?.navigationRouter.popped(route: route)
            }
        case .externalUrl:
            fatalError("Can't navigate to externalUrl")
        }
        routes.append(newRoute)
    }
}
