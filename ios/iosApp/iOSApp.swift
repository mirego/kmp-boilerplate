import Shared
import SwiftUI

@main
struct IOSApp: App {
    @UIApplicationDelegateAdaptor var appDelegate: AppDelegate

	var body: some Scene {
		WindowGroup {
            ExampleView(viewModel: appDelegate.viewModelFactory.exampleViewModel())
		}
	}
}
