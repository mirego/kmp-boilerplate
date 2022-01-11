import SwiftUI
import shared

struct Main: View {

    private let router: Router
    private let viewModelFactory: ViewModelFactory

    init(router: Router = MainRouter(),
         viewModelFactory: ViewModelFactory = MobileViewModelFactory()) {
        self.router = router
        self.viewModelFactory = viewModelFactory
    }

    @ObservedObject var screen = ObservableFlowWrapper<Screen>(MainRouter().screen, initial: Screen.Home())

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
