import Shared
import SwiftUI
import Trikot

struct ProjectsView: View {
    @ObservedObject private var observableViewModel: ObservableViewModelAdapter<ProjectsViewModel>

    init(viewModel: ProjectsViewModel) {
        observableViewModel = viewModel.asObservable()
    }

    var viewModel: ProjectsViewModel {
        observableViewModel.viewModel
    }

    var body: some View {
        contentView
            .frame(maxWidth: .infinity, maxHeight: .infinity)
            .background(
                Color(.primaryBlack)
                    .ignoresSafeArea()
            )
            .handleNavigation(viewModel, route: viewModel.navigationRoute, navigationTypeOverride: navigationTypeOverride)
    }

    @ViewBuilder private var contentView: some View {
        if let rootContent = viewModel.rootContent {
            switch onEnum(of: rootContent) {
                case let .content(content):
                    ProjectsContentView(viewModel: content.sections)
                case let .error(error):
                    ErrorView(viewModel: error.errorViewModel)
            }
        }
    }
}

extension ProjectsView {
    func navigationTypeOverride(route: VMDNavigationRoute) -> NavigationType? {
        if route is NavigationRouteProjectDetails {
            return .push
        }
        
        return nil
    }
}

#Preview {
    ProjectsView(
        viewModel: factoryPreview().createProjects(
            previewState: PreviewStateDataEmpty()
        )
    )
}

#Preview {
    ProjectsView(
        viewModel: factoryPreview().createProjects(
            previewState: PreviewStateError()
        )
    )
}
