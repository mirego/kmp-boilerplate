import SwiftUI
import Shared

extension View {
    func demoNavigation(navigationManager: DemoNavigationManager) -> some View {
        modifier(DemoNavigationModifier(navigationManager: navigationManager))
    }
}

private struct DemoNavigationModifier : ViewModifier {
    let navigationManager: DemoNavigationManager

    func body(content: Content) -> some View {
        content
            .navigation(navigationManager: navigationManager) { route in
                switch onEnum(of: route) {
                case .root:
                    fatalError("Can't nativate to root")
                case .tab1:
                    fatalError("Can't nativate to tab1")
                case .tab2:
                    fatalError("Can't nativate to tab2")
                case .screen1(let route):
                    Screen1View(viewModel: navigationManager.createScreen1(route: route))
                case .screen2(let route):
                    Screen2View(viewModel: navigationManager.createScreen2(route: route))
                case .screen3(let route):
                    Screen3View(viewModel: navigationManager.createScreen3(route: route))
                case .dialog:
                    Text("Dialog")
                case .externalUrl:
                    Text("ExternalUrl")
                }
            } buildNavigation: { route in
                let dismissCallback: () -> Void = {
                    navigationManager.popped(route: route)
                }

                switch onEnum(of: route) {
                case .root:
                    fatalError("Can't navigate to root")
                case .tab1:
                    fatalError("Can't navigate to tab1")
                case .tab2:
                    fatalError("Can't navigate to tab2")
                case .screen1:
                    return .push(screen: route, onDismiss: dismissCallback)
                case .screen2:
                    return .fullScreenCover(screen: route, embedInNavigationView: false, onDismiss: dismissCallback)
                case .screen3:
                    return .sheet(screen: route, embedInNavigationView: true, onDismiss: dismissCallback)
                case .dialog:
                    return .sheet(screen: route, embedInNavigationView: false, onDismiss: dismissCallback)
                case .externalUrl:
                    fatalError("Can't navigate to externalUrl")
                }
            }
    }
}
