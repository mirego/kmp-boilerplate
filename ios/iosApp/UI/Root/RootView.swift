import Shared
import Pilot
import SwiftUI
import Trikot

struct RootView: View {
    private var viewModel: RootViewModel

    init(viewModel: RootViewModel) {
        self.viewModel = viewModel
    }

    var body: some View {
        ProjectsView(viewModel: viewModel.projectsViewModel)
            .embedInNavigationView()
            .pilotStyle()
    }
}

struct RootView_Previews: PreviewProvider {
    static var previews: some View {
        RootView(viewModel: previewsFactory().createRoot())
    }
}
