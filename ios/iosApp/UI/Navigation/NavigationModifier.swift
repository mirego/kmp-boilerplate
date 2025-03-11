import Shared
import Pilot
import SwiftUI
import Trikot

private struct NavigationModifier: ViewModifier {
    let navigationManager: NavigationManager
    let navigationTypeOverride: ((NavigationRoute) -> NavigationType?)?
    
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
        
        return switch navigationType(for: route) {
        case .sheet:
                .sheet(
                    screen: buildViewModelHolder(route: route),
                    data: NavigationTypeData(embedInNavigationView: false, onDismiss: onDismissClosure)
                )
        case .fullScreen:
                .fullScreenCover(
                    screen: buildViewModelHolder(route: route),
                    data: NavigationTypeData(embedInNavigationView: false, onDismiss: onDismissClosure)
                )
        case .push:
                .push(
                    screen: buildViewModelHolder(route: route),
                    onDismiss: onDismissClosure
                )
        }
    }
    
    private func buildViewModelHolder(route: NavigationRoute) -> ViewModelHolder {
        return switch onEnum(of: route) {
        case .projectDetails(let route):
            ViewModelHolder.projectDetails(
                koin.projectDetailsViewModel(
                    navigationManager: navigationManager,
                    route: route
                )
            )
        }
    }
    
    private func navigationType(for route: NavigationRoute) -> NavigationType {
        if let overridenNavigationType = navigationTypeOverride?(route) {
            return overridenNavigationType
        }
        
        switch onEnum(of: route) {
        case .projectDetails:
            return .push
        }
    }
    
    private func handleAction(action: NavigationAction) {}
}

extension View {
    func handleNavigation(navigationManager: NavigationManager, navigationTypeOverride: ((NavigationRoute) -> NavigationType?)? = nil) -> some View {
        modifier(
            NavigationModifier(
                navigationManager: navigationManager,
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

enum ViewModelHolder {
    case projectDetails(ProjectDetailsViewModel)
}
