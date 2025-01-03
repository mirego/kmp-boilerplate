import Shared
import Pilot
import SwiftUI
import Trikot

struct ProjectsView: View {
    @StateObject private var viewModelLifecycle: ViewModelLifecycleHandler<ProjectsViewModel>
    @ObservedObject private var rootContentObservable: StateObservable<ProjectsRoot>

    init(viewModel: ProjectsViewModel) {
        _viewModelLifecycle = StateObject(wrappedValue: ViewModelLifecycleHandler(viewModel: viewModel))
        _rootContentObservable = ObservedObject(wrappedValue: StateObservable(viewModel.rootContent))
    }

    var viewModel: ProjectsViewModel {
        viewModelLifecycle.viewModel
    }

    var body: some View {
        contentView
            .frame(maxWidth: .infinity, maxHeight: .infinity)
            .background(
                Color(.primaryBlack)
                    .ignoresSafeArea()
            )
            .handleNavigation(navigationManager: viewModel.navigationManager)
    }

    @ViewBuilder private var contentView: some View {
        switch onEnum(of: rootContentObservable.value) {
            case let .content(content):
                ProjectsContentView(projectsContentSections: content.sections)
            case let .error(error):
                ErrorView(viewModel: error.errorViewModel)
        }
    }
}

extension ProjectsView {
//    func navigationTypeOverride(route: VMDNavigationRoute) -> NavigationType? {
//        if route is NavigationRouteProjectDetails {
//            return .push
//        }
//        
//        return nil
//    }
}

#Preview {
    ProjectsView(
        viewModel: previewsFactory().createProjects(
            previewState: PreviewStateDataEmpty()
        )
    )
}

#Preview {
    ProjectsView(
        viewModel: previewsFactory().createProjects(
            previewState: PreviewStateError()
        )
    )
}
