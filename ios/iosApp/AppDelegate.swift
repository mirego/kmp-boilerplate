import Shared
import UIKit

class AppDelegate: NSObject, UIApplicationDelegate {
    lazy var applicationViewModel: ApplicationViewModel = {
        Application.koin.applicationViewModel()
    }()

    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]? = nil
    ) -> Bool {
        AppInitializer.initializeComponents()
        return true
    }
}
