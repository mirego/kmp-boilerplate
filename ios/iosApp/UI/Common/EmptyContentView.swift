import Shared
import SwiftUI
import Pilot

struct EmptyContentView: View {
    private let viewModel: EmptyViewModel
    private let padding: CGFloat = 16

    init(viewModel: EmptyViewModel) {
        self.viewModel = viewModel
    }

    var body: some View {
        VStack(spacing: 0) {
            PilotLocalImage(viewModel.icon)
                .resizable()
                .frame(width: 55, height: 55)
                .foregroundStyle(Color.white)

            Text(viewModel.title)
                .textStyle(.largeTitle, .regular, .white)
                .lineLimit(1)
                .padding(.top, padding * 2)

            Text(viewModel.message)
                .textStyle(.body, .regular, .white)
                .multilineTextAlignment(.center)
                .padding(.top, padding)
        }
    }
}
