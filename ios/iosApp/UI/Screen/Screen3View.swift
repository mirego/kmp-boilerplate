import SwiftUI
import Shared
import Trikot

struct Screen3View: View {
    @ObservedObject private var observableViewModel: ObservableViewModelAdapter<Screen3ViewModel>

    init(viewModel: Screen3ViewModel) {
        observableViewModel = viewModel.asObservable()
    }

    var viewModel: Screen3ViewModel {
        observableViewModel.viewModel
    }

    var body: some View {
        VStack(spacing: 32) {
            Text(viewModel.title)
                .padding(.top, 30)

            Spacer()

            VMDButton(viewModel.pushButton) {
                Text($0.text)
            }

            VMDButton(viewModel.closeButton) {
                Text($0.text)
            }

            VMDButton(viewModel.dialogButton) {
                Text($0.text)
            }

            Spacer()
        }
        .frame(maxWidth: .infinity)
        .background(Color.black.opacity(0.1))
    }
}

#Preview {
    Screen3View(viewModel: factoryPreview().createScreen3())
}
