import SwiftUI
import Shared

extension View {
    func demoNavigation(navigationManager: DemoNavigationManager) -> some View {
        modifier(DemoNavigationModifier(navigationManager: navigationManager))
    }
}

private struct DemoNavigationModifier: ViewModifier {
    let navigationManager: DemoNavigationManager

    func body(content: Content) -> some View {
        content
            .navigation(navigationManager: navigationManager) { (viewModelHolder: ViewModelHolder) in
                switch viewModelHolder {
                case .screen1(let viewModel):
                    Screen1View(viewModel: viewModel)
                case .screen2(let viewModel):
                    Screen2View(viewModel: viewModel)
                case .screen3(let viewModel):
                    Screen3View(viewModel: viewModel)
                case .dialog(let viewModel):
                    DialogTransitionView {
                        DialogView(viewModel: viewModel)
                    } tapAround: {
                        viewModel.closeButton.actionBlock()
                    }
                }
            } buildNavigation: { _, route in
                let dismissCallback: () -> Void = {
                    navigationManager.poppedFrom(route: route)
                }
                switch onEnum(of: route) {
                case .screen1(let route):
                    return .push(screen: ViewModelHolder.screen1(navigationManager.createScreen1(route: route)), onDismiss: dismissCallback)
                case .screen2(let route):
                    return .fullScreenCover(screen: ViewModelHolder.screen2(navigationManager.createScreen2(route: route)), embedInNavigationView: false, onDismiss: dismissCallback)
                case .screen3(let route):
                    return .sheet(screen: ViewModelHolder.screen3(navigationManager.createScreen3(route: route)), embedInNavigationView: true, onDismiss: dismissCallback)
                case .dialog(let route):
                    return .fullScreenNotAnimated(screen: ViewModelHolder.dialog(navigationManager.createDialog(route: route)), embedInNavigationView: false, onDismiss: dismissCallback, popDelayInSeconds: 0.5)
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
