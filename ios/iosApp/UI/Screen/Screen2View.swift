import SwiftUI
import Shared
import Trikot

struct Screen2View: View {
    @ObservedObject private var observableViewModel: ObservableViewModelAdapter<Screen2ViewModel>

    init(viewModel: Screen2ViewModel) {
        observableViewModel = viewModel.asObservable()
    }

    var viewModel: Screen2ViewModel {
        observableViewModel.viewModel
    }

    var body: some View {
        VStack(spacing: 32) {
            Text(viewModel.title)
                .padding(.top, 30)

            Spacer()

            VMDButton(viewModel.closeButton) {
                Text($0.text)
            }

            Spacer()
        }
        .frame(maxWidth: .infinity)
        .background(Color.black.opacity(0.1))
    }
}

#Preview {
    Screen2View(viewModel: factoryPreview().createScreen2())
}
