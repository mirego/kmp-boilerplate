import SwiftUI
import Shared
import Trikot

struct Screen1View: View {
    @ObservedObject private var observableViewModel: ObservableViewModelAdapter<Screen1ViewModel>

    init(viewModel: Screen1ViewModel) {
        observableViewModel = viewModel.asObservable()
    }

    var viewModel: Screen1ViewModel {
        observableViewModel.viewModel
    }

    var body: some View {
        let _ = Self._printChanges()
        VStack(spacing: 32) {
            Text(viewModel.title)

            Spacer()
            
            VMDButton(viewModel.closeButton) {
                Text($0.text)
            }

            VMDButton(viewModel.modalButton) {
                Text($0.text)
            }

            VMDButton(viewModel.pushButton) {
                Text($0.text)
            }

            Spacer()
        }
        .frame(maxWidth: .infinity)
        .background(Color.black.opacity(0.1))
    }
}

#Preview {
    Screen1View(viewModel: factoryPreview().createScreen1())
}
