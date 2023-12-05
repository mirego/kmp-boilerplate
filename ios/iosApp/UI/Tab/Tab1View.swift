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
        if #available(iOS 16, *) {
            NavigationStack {
                content
            }
        } else {
            NavigationView {
                content
            }
            .navigationViewStyle(.stack)
        }
    }
    
    private var content: some View {
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

            VMDText(viewModel.dialogResult)

            Spacer()
        }
        .frame(maxWidth: .infinity)
        .background(Color.black.opacity(0.1))
        .demoNavigation(navigationManager: viewModel.navigationManager)
    }
}

#Preview {
    Tab1View(viewModel: factoryPreview().createTab1())
}
