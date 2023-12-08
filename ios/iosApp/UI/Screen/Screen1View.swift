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
        VStack(spacing: 32) {
            VMDText(viewModel.title)

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

            VMDButton(viewModel.popToRoot) {
                Text($0.text)
            }

            VMDButton(viewModel.popToScreen3Inclusive) {
                Text($0.text)
            }

            VMDButton(viewModel.popToScreen3Exclusive) {
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
