import SwiftUI

@main
struct IOSApp: App {
    @UIApplicationDelegateAdaptor(AppDelegate.self) var appDelegate

    @StateObject private var navigationTransitionState = NavigationTransitionDataState()

    var body: some Scene {
        WindowGroup {
            ApplicationView(viewModel: appDelegate.applicationViewModel)
                .environmentObject(navigationTransitionState)
        }
    }
}
