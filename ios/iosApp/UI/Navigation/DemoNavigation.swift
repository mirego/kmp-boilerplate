import SwiftUI
import Shared

extension View {
    func demoNavigation(navigationManager: DemoNavigationManager) -> some View {
        modifier(DemoNavigationModifier(navigationManager: navigationManager))
    }
}

private struct DemoNavigationModifier: ViewModifier {
    let navigationManager: DemoNavigationManager

    @EnvironmentObject private var navigationTransitionState: NavigationTransitionDataState

    @Environment(\.openURL) private var openURL

    func body(content: Content) -> some View {
        content
            .navigation(
                navigationManager: navigationManager,
                buildView: buildView,
                buildNavigation: buildNavigation,
                handleAction: handleAction
            )
    }

    @ViewBuilder
    private func buildView(viewModelHolder: ViewModelHolder) -> some View {
        switch viewModelHolder {
        case .screen1(let viewModel):
            Screen1View(viewModel: viewModel)
        case .screen2(let viewModel):
            Screen2View(viewModel: viewModel, fromRect: navigationTransitionState.screen2Data() ?? .zero)
        case .screen3(let viewModel):
            Screen3View(viewModel: viewModel)
        case .dialog(let viewModel):
            DialogTransitionView {
                DialogView(viewModel: viewModel)
            } tapAround: {
                viewModel.closeButton.actionBlock()
            }
        }
    }

    private func buildNavigation(routes: [DemoNavigationRoute], route: DemoNavigationRoute) -> NavigationType<ViewModelHolder, EmptyViewModifier> {
        let dismissCallback: () -> Void = {
            navigationManager.poppedFrom(route: route)
        }
        switch onEnum(of: route) {
        case .screen1(let route):
            return .push(screen: ViewModelHolder.screen1(navigationManager.createScreen1(route: route)), onDismiss: dismissCallback)
        case .screen2(let route):
            return .fullScreenNotAnimated(
                screen: ViewModelHolder.screen2(navigationManager.createScreen2(route: route)),
                data: NavigationTypeData(embedInNavigationView: false, onDismiss: dismissCallback),
                popDelayInSeconds: 0.3
            )
        case .screen3(let route):
            let viewModelHolder = ViewModelHolder.screen3(navigationManager.createScreen3(route: route))
            if routes.last?.name == DemoNavigationRouteName.screen2.name {
                return .fullScreenCover(
                    screen: viewModelHolder,
                    data: NavigationTypeData(embedInNavigationView: true, onDismiss: dismissCallback)
                )
            } else {
                return .sheet(
                    screen: viewModelHolder,
                    data: NavigationTypeData(embedInNavigationView: true, onDismiss: dismissCallback)
                )
            }
        case .dialog(let route):
            return .fullScreenNotAnimated(
                screen: ViewModelHolder.dialog(navigationManager.createDialog(route: route)),
                data: NavigationTypeData(embedInNavigationView: false, onDismiss: dismissCallback),
                popDelayInSeconds: 0.5
            )
        }
    }

    private func handleAction(action: NavigationAction) {
        switch onEnum(of: action) {
        case .externalUrl(let action):
            if let url = URL(string: action.url) {
                openURL(url)
            }
        case .openSettings:
            if let url = URL(string: UIApplication.openSettingsURLString) {
                openURL(url)
            }
        }
    }
}

enum ViewModelHolder {
    case screen1(Screen1ViewModel)
    case screen2(Screen2ViewModel)
    case screen3(Screen3ViewModel)
    case dialog(DialogViewModel)
}
