import SwiftUI
import shared

struct Main: View {

    private let viewModelFactory: ViewModelFactory

    @ObservedObject var screen: ObservableFlowWrapper<Screen>

    init(router: Router = MainRouter(),
         viewModelFactory: ViewModelFactory = MobileViewModelFactory()) {
        self.viewModelFactory = viewModelFactory

        screen = ObservableFlowWrapper<Screen>(router.screen, initial: Screen.Home())
    }

    var body: some View {
        switch screen.value {
        case _ as Screen.Home:
            Home(viewModel: viewModelFactory.homeViewModel)
        case let screen as Screen.Example:
            Example(viewModel: viewModelFactory.exampleViewModel(origin: screen.origin))
        default:
            fatalError("Unsupported screen \(screen.value)")
        }
    }
}
