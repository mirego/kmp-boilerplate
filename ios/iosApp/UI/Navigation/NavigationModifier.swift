import Shared
import Pilot
import SwiftUI
import Trikot

private struct NavigationModifier: ViewModifier {
    let navigationManager: NavigationManager
    
//    let navigationTypeOverride: ((VMDNavigationRoute) -> NavigationType?)?
    
    private let koin = Application.koin

    func body(content: Content) -> some View {
        content
            .pilotNavigation(
                navigationManager: navigationManager,
                buildView: buildView,
                buildNavigation: buildNavigation,
                handleAction: handleAction
            )
    }

    @ViewBuilder  private func buildView(viewModelHolder: ViewModelHolder) -> some View {
        switch viewModelHolder {
        case .projectDetails(let viewModel):
            ProjectDetailsView(viewModel: viewModel)
        }
    }
    
    private func buildNavigation(routes: [NavigationRoute], route: NavigationRoute) -> PilotNavigationType<ViewModelHolder, EmptyViewModifier>? {
        let onDismissClosure: () -> Void = {
            navigationManager.poppedFrom(route: route)
        }
        
        return switch onEnum(of: route) {
        case .projectDetails(let route):
                .sheet(
                    screen: ViewModelHolder.projectDetails(
                        koin.projectDetailsViewModel(
                            navigationManager: navigationManager,
                            route: route
                        )
                    ),
                    data: NavigationTypeData(embedInNavigationView: false, onDismiss: onDismissClosure)
                )
        }
    }
    
    private func handleAction(action: NavigationAction) {
        // NO-OP
    }
}

extension View {
    func handleNavigation(navigationManager: NavigationManager) -> some View {
        modifier(
            NavigationModifier(
                navigationManager: navigationManager
            )
        )
    }
}

enum NavigationType {
    case sheet
    case fullScreen
    case push
}

enum ViewModelHolder {
    case projectDetails(ProjectDetailsViewModel)
}


    
//    private func navigationType(for route: VMDNavigationRoute?) -> NavigationType? {
//        if let route, let overridenNavigationType = navigationTypeOverride?(route) {
//            return overridenNavigationType
//        }
//
//        if route is NavigationRouteProjectDetails {
//            return .push
//        }
//
//        return nil
//    }
    
    



//    .sheet(route: navigationType(for: route) == .sheet ? route : nil) { route in
//        buildView(for: route)
//    }
//    .fullScreen(route: navigationType(for: route) == .fullScreen ? route : nil) { route in
//        buildView(for: route)
//    }
//    .push(route: navigationType(for: route) == .push ? route : nil) { route in
//        buildView(for: route)
//    }
