import SwiftUI
import shared

struct Home: View {

    private let viewModel: HomeViewModel

    @ObservedObject var message: ObservableFlowWrapper<NSString>
    @ObservedObject var buttonText: ObservableFlowWrapper<NSString>

    init(viewModel: HomeViewModel) {
        self.viewModel = viewModel

        message = ObservableFlowWrapper<NSString>(viewModel.greetingMessage, initial: "")
        buttonText = ObservableFlowWrapper<NSString>(viewModel.buttonText, initial: "")
    }

	var body: some View {
        VStack(alignment: .center, spacing: 16) {
            Text("\(message.value)")

            Button("\(buttonText.value)", action: viewModel.onButtonClick)
        }
	}
}

struct Home_Previews: PreviewProvider {
	static var previews: some View {
        Home(viewModel: HomeViewModelPreview())
	}
}
