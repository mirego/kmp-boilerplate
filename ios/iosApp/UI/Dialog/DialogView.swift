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
                .padding(.top, 32)

            Spacer()

            VMDButton(viewModel.closeButton) {
                Text($0.text)
            }

            ForEach(viewModel.button.indices, id: \.self) { index in
                VMDButton(viewModel.button[index]) {
                    Text($0.text)
                }
            }

            Spacer()
        }
        .frame(maxWidth: .infinity)
        .background(Color.black.opacity(0.1))
    }
}

#Preview {
    DialogView(viewModel: factoryPreview().createDialog())
}
