import Shared
import SwiftUI

@main
struct IOSApp: App {
    let viewModelFactory = ViewModelFactory()

    var body: some Scene {
        WindowGroup {
            ExampleView(
                viewModel: viewModelFactory.example()
            )
        }
    }
}
