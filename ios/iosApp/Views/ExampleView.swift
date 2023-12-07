import Shared
import SwiftUI

struct ExampleView: View {

    private let viewModel: ExampleViewModel

    @State private var state: ExampleViewModelState

    init(viewModel: ExampleViewModel) {
        self.viewModel = viewModel
        self.state = viewModel.state.value
    }

    var body: some View {
        VStack {
            Text(state.greeting)
        }
        .task {
            await withTaskCancellationHandler {
                for await state in viewModel.state {
                    self.state = state
                }
            } onCancel: {
                viewModel.cancel()
            }
        }
    }
}

#Preview {
    PreviewContext { viewModelFactory in
        ExampleView(
            viewModel: viewModelFactory.example()
        )
    }
}
