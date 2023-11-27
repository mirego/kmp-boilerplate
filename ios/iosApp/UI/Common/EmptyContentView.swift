import Shared
import SwiftUI
import Trikot

struct EmptyContentView: View {
    @ObservedObject private var observableViewModel: ObservableViewModelAdapter<EmptyViewModel>
    private let padding: CGFloat = 16

    private var viewModel: EmptyViewModel {
        observableViewModel.viewModel
    }

    init(viewModel: EmptyViewModel) {
        observableViewModel = viewModel.asObservable()
    }

    var body: some View {
        VStack(spacing: 0) {
            VMDImage(viewModel.icon)
                .resizable()
                .frame(width: 55, height: 55)
                .foregroundStyle(Color.white)

            VMDText(viewModel.title)
                .textStyle(.largeTitle, .regular, .white)
                .lineLimit(1)
                .padding(.top, padding * 2)

            VMDText(viewModel.message)
                .textStyle(.body, .regular, .white)
                .multilineTextAlignment(.center)
                .padding(.top, padding)
        }
    }
}
