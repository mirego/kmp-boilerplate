import SwiftUI
import Shared
import Trikot

struct Tab2View: View {
    @ObservedObject private var observableViewModel: ObservableViewModelAdapter<Tab2ViewModel>

    init(viewModel: Tab2ViewModel) {
        observableViewModel = viewModel.asObservable()
    }

    var viewModel: Tab2ViewModel {
        observableViewModel.viewModel
    }

    var body: some View {
        let _ = Self._printChanges()
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
    Tab2View(viewModel: factoryPreview().createTab2())
}
