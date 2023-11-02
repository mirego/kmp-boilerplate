import Shared
import SwiftUI
import Trikot

struct ProjectDetailsView: View {
    @ObservedObject private var observableViewModel: ObservableViewModelAdapter<ProjectDetailsViewModel>
    
    init(viewModel: ProjectDetailsViewModel) {
        observableViewModel = viewModel.asObservable()
    }
    
    var viewModel: ProjectDetailsViewModel {
        observableViewModel.viewModel
    }
    
    var body: some View {
        Color(.primaryBlack)
    }
}

#Preview {
    ProjectDetailsView(
        viewModel: factoryPreview().createProjectDetails(previewState: PreviewStateDataContent())
    )
}
