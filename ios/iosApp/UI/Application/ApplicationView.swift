import Shared
import SwiftUI
import Trikot

struct ApplicationView: View {
    @ObservedObject private var observableViewModel: ObservableViewModelAdapter<ApplicationViewModel>
    @StateObject private var statusBarConfigurator = StatusBarConfigurator()
    
    init(viewModel: ApplicationViewModel) {
        observableViewModel = viewModel.asObservable()
    }

    var viewModel: ApplicationViewModel {
        observableViewModel.viewModel
    }

    var body: some View {
        RootView(viewModel: viewModel.rootViewModel)
            .prepareStatusBarConfigurator(statusBarConfigurator)
            .environment(\.statusBarConfigurator, statusBarConfigurator)
            .onAppear {
                statusBarConfigurator.statusBarStyle = .lightContent
            }
    }
}

struct ApplicationView_Previews: PreviewProvider {
    static var previews: some View {
        ApplicationView(viewModel: factoryPreview().createApplication())
    }
}
