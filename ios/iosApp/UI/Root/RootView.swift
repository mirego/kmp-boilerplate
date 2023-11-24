import Shared
import SwiftUI
import Trikot

struct RootView: View {
    @ObservedObject private var observableViewModel: ObservableViewModelAdapter<RootViewModel>

    init(viewModel: RootViewModel) {
        observableViewModel = viewModel.asObservable()
    }

    var viewModel: RootViewModel {
        observableViewModel.viewModel
    }

    var body: some View {
        Text("Hi")
        

    }
}

struct RootView_Previews: PreviewProvider {
    static var previews: some View {
        RootView(viewModel: factoryPreview().createRoot())
    }
}
