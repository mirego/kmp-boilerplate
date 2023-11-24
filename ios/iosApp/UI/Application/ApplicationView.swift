import Shared
import SwiftUI
import Trikot

struct ApplicationView: View {
    @ObservedObject private var observableViewModel: ObservableViewModelAdapter<ApplicationViewModel>

    init(viewModel: ApplicationViewModel) {
        observableViewModel = viewModel.asObservable()
    }

    var viewModel: ApplicationViewModel {
        observableViewModel.viewModel
    }

    var body: some View {
        RootView(viewModel: viewModel.rootViewModel)
    }
}

struct ApplicationView_Previews: PreviewProvider {
    static var previews: some View {
        ApplicationView(viewModel: factoryPreview().createApplication())
    }
}
