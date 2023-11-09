import Shared
import SwiftUI

struct ButtonView: View {

    private let viewModel: ButtonViewModel

    @State private var state: ButtonViewModelState

    init(_ viewModel: ButtonViewModel) {
        self.viewModel = viewModel
        self.state = viewModel.state.value
    }

    var body: some View {
        Button(state.text ?? "", action: viewModel.action)
            .disabled(!state.enabled)
            .task {
                for await state in viewModel.state {
                    self.state = state
                }
            }
    }
}
