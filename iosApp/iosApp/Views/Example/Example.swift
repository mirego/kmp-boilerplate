import SwiftUI
import shared

struct Example: View {

    private let viewModel: ExampleViewModel

    @ObservedObject var message: ObservableFlowWrapper<NSString>
    @ObservedObject var backButtonText: ObservableFlowWrapper<NSString>

    init(viewModel: ExampleViewModel) {
        self.viewModel = viewModel

        message = ObservableFlowWrapper<NSString>(viewModel.exampleMessage, initial: "")
        backButtonText = ObservableFlowWrapper<NSString>(viewModel.backButtonText, initial: "")
    }

    var body: some View {
        VStack(alignment: .center, spacing: 16) {
            Text("\(message.value)")

            Button("\(backButtonText.value)", action: viewModel.onBackButtonClick)
        }
    }

}

struct Example_Previews: PreviewProvider {
	static var previews: some View {
        Example(viewModel: ExampleViewModelPreview())
	}
}
