import Shared
import SwiftUI
import Trikot

struct NavigationModifier: ViewModifier {
    let viewModel: VMDNavigationViewModel
    let route: VMDNavigationRoute?

    func body(content: Content) -> some View {
        content
            .sheet(route: route as? NavigationRouteProjectDetails) { route in
                ProjectDetailsView(viewModel: route.viewModel)
            }
    }
}

extension View {
    func handleNavigation(_ viewModel: VMDNavigationViewModel, route: VMDNavigationRoute?) -> some View {
        modifier(NavigationModifier(viewModel: viewModel, route: route))
    }
}
