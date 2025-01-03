import Shared
import Pilot
import SwiftUI
import Trikot

struct ApplicationView: View {
    @StateObject private var viewModelLifecycle: ViewModelLifecycleHandler<ApplicationViewModel>
    @StateObject private var statusBarConfigurator = StatusBarConfigurator()
    
    init(viewModel: ApplicationViewModel) {
        _viewModelLifecycle = StateObject(wrappedValue: ViewModelLifecycleHandler(viewModel: viewModel))
    }

    var viewModel: ApplicationViewModel {
        viewModelLifecycle.viewModel
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
        ApplicationView(viewModel: previewsFactory().createApplication())
    }
}
