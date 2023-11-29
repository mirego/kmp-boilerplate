import SwiftUI
import Shared
import Trikot

struct Tab1View: View {
    @ObservedObject private var observableViewModel: ObservableViewModelAdapter<Tab1ViewModel>

    init(viewModel: Tab1ViewModel) {
        observableViewModel = viewModel.asObservable()
    }

    var viewModel: Tab1ViewModel {
        observableViewModel.viewModel
    }

    var body: some View {
        NavigationView {
            VStack(spacing: 32) {
                Text(viewModel.title)

                Spacer()

                VMDButton(viewModel.pushButton) {
                    Text($0.text)
                }

                VMDButton(viewModel.modalButton) {
                    Text($0.text)
                }

                VMDButton(viewModel.dialogButton) {
                    Text($0.text)
                }

                Spacer()
            }
            .frame(maxWidth: .infinity)
            .background(Color.black.opacity(0.1))
            .navigationRouter(viewModel.navigationManager)
        }
        .navigationViewStyle(.stack)
    }
}

#Preview {
    Tab1View(viewModel: factoryPreview().createTab1())
}
