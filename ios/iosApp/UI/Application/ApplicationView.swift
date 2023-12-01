import Shared
import SwiftUI
import Trikot

struct ApplicationView: View {
    @ObservedObject private var observableViewModel: ObservableViewModelAdapter<ApplicationViewModel>

    init(viewModel: ApplicationViewModel) {
        observableViewModel = viewModel.asObservable()
    }

    var viewModel: ApplicationViewModel {
        observableViewModel.viewModel
    }

    var body: some View {
        TabView {
            Tab1View(viewModel: viewModel.tab1ViewModel)
                .tabItem {
                    Label(
                        title: { Text("Tab 1") },
                        icon: { Image(systemName: "house") }
                    )
                }

            Tab2View(viewModel: viewModel.tab2ViewModel)
                .tabItem {
                    Label(
                        title: { Text("Tab 2") },
                        icon: { Image(systemName: "person") }
                    )
                }
        }
        .demoNavigation(navigationManager: viewModel.navigationManager)
    }
}

struct ApplicationView_Previews: PreviewProvider {
    static var previews: some View {
        ApplicationView(viewModel: factoryPreview().createApplication())
    }
}
