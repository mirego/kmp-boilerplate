import Kingfisher
import Shared
import SwiftUI

struct ImageView: View {

    private let viewModel: ImageViewModel

    @State private var state: ImageViewModelState

    init(_ viewModel: ImageViewModel) {
        self.viewModel = viewModel
        self.state = viewModel.state.value
    }

    var body: some View {
        KFImage.url(URL(string: state.url)!)
            .placeholder { p in
                ProgressView(p)
            }
            .resizable()
            .task {
                for await state in viewModel.state {
                    self.state = state
                }
            }
    }
}
