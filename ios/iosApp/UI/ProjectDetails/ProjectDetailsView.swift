import Shared
import SwiftUI
import Pilot

struct ProjectDetailsView: View {
    @StateObject private var viewModelLifecycle: ViewModelLifecycleHandler<ProjectDetailsViewModel>
    @ObservedObject private var rootContentObservable: StateObservable<ProjectDetailsRoot>

    init(viewModel: ProjectDetailsViewModel) {
        _viewModelLifecycle = StateObject(wrappedValue: ViewModelLifecycleHandler(viewModel: viewModel))
        _rootContentObservable = ObservedObject(wrappedValue: StateObservable(viewModel.rootContent))
    }

    var viewModel: ProjectDetailsViewModel {
        viewModelLifecycle.viewModel
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
                PilotButtonView(viewModel.closeButton) { content in
                    PilotLocalImage(content)
                        .renderingMode(.template)
                        .resizable()
                        .frame(width: 35, height: 35)
                        .foregroundStyle(Color.white)
                        .contentShape(Rectangle())
                }
            }
        }
    }

    @ViewBuilder private var contentView: some View {
        switch onEnum(of: rootContentObservable.value) {
            case let .content(content):
                ProjectDetailsContentView(viewModel: content)
            case let .error(error):
                ErrorView(viewModel: error.errorViewModel)
        }
    }
}

#Preview {
    ProjectDetailsView(
        viewModel: previewsFactory().createProjectDetails(previewState: PreviewStateDataContent())
    )
}
