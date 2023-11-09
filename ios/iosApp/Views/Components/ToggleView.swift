import Shared
import SwiftUI

struct ToggleView: View {

    private let viewModel: ToggleViewModel

    @State private var state: ToggleViewModelState

    init(_ viewModel: ToggleViewModel) {
        self.viewModel = viewModel
        self.state = viewModel.state.value
    }

    private var isOn: Binding<Bool> {
        Binding {
            state.isOn
        } set: { isOn in
            viewModel.toggle(isOn: isOn)
        }
    }

    var body: some View {
        Toggle(state.text ?? "", isOn: isOn)
            .disabled(!state.enabled)
            .task {
                for await state in viewModel.state {
                    self.state = state
                }
            }
    }
}
