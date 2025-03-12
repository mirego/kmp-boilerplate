import Shared
import SwiftUI
import Pilot

struct ErrorView: View {
    private let viewModel: ErrorViewModel
    private let padding: CGFloat = 16

    init(viewModel: ErrorViewModel) {
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

            PilotButtonView(viewModel.retryButton) { content in
                Text(content as String)
                    .textStyle(.body, .regular, .white)
                    .frame(maxWidth: 320)
            }
            .padding(.vertical, 12)
            .background(
                Capsule()
                    .fill(Color(.accentOrange))
            )
            .padding(.top, padding * 2)
            .padding(.horizontal, padding)
        }
    }
}
