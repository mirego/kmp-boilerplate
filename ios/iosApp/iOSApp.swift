import SwiftUI

@main
struct IOSApp: App {
    @UIApplicationDelegateAdaptor(AppDelegate.self) var appDelegate

    var body: some Scene {
        WindowGroup {
            ApplicationView(viewModel: appDelegate.applicationViewModel)
        }
    }
}
