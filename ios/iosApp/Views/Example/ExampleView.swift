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
            Text(state.text)

            LazyVStack {
                ForEach(state.items, id: \.identifier) { item in
                    Text(item.text)
                }
            }

            ButtonView(viewModel.button)

            ToggleView(viewModel.toggle)

            ImageView(viewModel.image)
                .aspectRatio(contentMode: .fit)
        }
        .padding()
        .task {
            for await state in viewModel.state {
                self.state = state
            }
        }
    }
}

#Preview {
    PreviewProvider { viewModelFactory in
        ExampleView(viewModel: viewModelFactory.exampleViewModel())
    }
}
