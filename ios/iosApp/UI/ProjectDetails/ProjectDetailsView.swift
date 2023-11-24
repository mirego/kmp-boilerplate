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
        ZStack(alignment: .topLeading) {
            contentView
                .ignoresSafeArea(edges: .top)
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .background(
            viewModel.backgroundColor.color
                .ignoresSafeArea()
        )
        .navigationBarBackButtonHidden()
        .toolbar {
            ToolbarItem(placement: .topBarLeading) {
                VMDButton(viewModel.closeButton) {
                    $0.image.image?
                        .renderingMode(.template)
                        .resizable()
                        .frame(width: 35, height: 35)
                        .foregroundStyle(Color.white)
                        .contentShape(Rectangle())
                }
            }
        }
        .handleNavigation(viewModel, route: viewModel.navigationRoute)
    }

    @ViewBuilder private var contentView: some View {
        if let root = viewModel.rootContent {
            switch onEnum(of: root) {
                case let .content(content):
                    ProjectDetailsContentView(viewModel: content)
                case let .error(error):
                    ErrorView(viewModel: error.errorViewModel)
            }
        }
    }
}

#Preview {
    ProjectDetailsView(
        viewModel: factoryPreview().createProjectDetails(previewState: PreviewStateDataContent())
    )
}
