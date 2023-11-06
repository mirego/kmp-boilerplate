import Shared
import UIKit

class AppDelegate: NSObject, UIApplicationDelegate {
    private lazy var bootstrapper = Bootstrapper(bootstrap: BootstrapImpl())

    lazy var applicationViewModel: ApplicationViewModel = {
        bootstrapper.applicationViewModel()
    }()

    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]? = nil
    ) -> Bool {
        AppInitializer.initializeComponents(environment: bootstrapper.bootstrap.environment)
        bootstrapper.doInitDependencies()

        return true
    }
}
