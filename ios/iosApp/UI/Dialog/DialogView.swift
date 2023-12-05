import SwiftUI
import Shared
import Trikot

struct DialogView: View {
    @ObservedObject private var observableViewModel: ObservableViewModelAdapter<DialogViewModel>

    init(viewModel: DialogViewModel) {
        observableViewModel = viewModel.asObservable()
    }

    var viewModel: DialogViewModel {
        observableViewModel.viewModel
    }

    var body: some View {
        VStack(spacing: 32) {
            Text(viewModel.title)

            VMDButton(viewModel.closeButton) {
                Text($0.text)
            }

            ForEach(viewModel.button.indices, id: \.self) { index in
                VMDButton(viewModel.button[index]) {
                    Text($0.text)
                }
            }
        }
        .frame(maxWidth: .infinity)
        .padding(.all, 32)
        .background(RoundedRectangle(cornerRadius: 16).fill(.white).shadow(color: .black.opacity(0.1), radius: 4))
        .padding(.horizontal, 32)
    }
}

#Preview {
    DialogView(viewModel: factoryPreview().createDialog())
}
