import Shared
import SwiftUI
import Trikot

struct NavigationModifier: ViewModifier {
    let viewModel: VMDNavigationViewModel
    let route: VMDNavigationRoute?
    let navigationTypeOverride: ((VMDNavigationRoute) -> NavigationType?)?

    func body(content: Content) -> some View {
        content
            .sheet(route: navigationType(for: route) == .sheet ? route : nil) { route in
                buildView(for: route)
            }
            .fullScreen(route: navigationType(for: route) == .fullScreen ? route : nil) { route in
                buildView(for: route)
            }
            .push(route: navigationType(for: route) == .push ? route : nil) { route in
                buildView(for: route)
            }
    }

    @ViewBuilder  private func buildView(for route: VMDNavigationRoute) -> some View {
        if let route = route as? NavigationRouteProjectDetails {
            ProjectDetailsView(viewModel: route.viewModel)
        }
    }

    private func navigationType(for route: VMDNavigationRoute?) -> NavigationType? {
        if let route, let overridenNavigationType = navigationTypeOverride?(route) {
            return overridenNavigationType
        }

        if route is NavigationRouteProjectDetails {
            return .push
        }

        return nil
    }
}

extension View {
    func handleNavigation(_ viewModel: VMDNavigationViewModel, route: VMDNavigationRoute?, navigationTypeOverride: ((VMDNavigationRoute) -> NavigationType?)? = nil) -> some View {
        modifier(
            NavigationModifier(
                viewModel: viewModel,
                route: route,
                navigationTypeOverride: navigationTypeOverride
            )
        )
    }
}

enum NavigationType {
    case sheet
    case fullScreen
    case push
}
