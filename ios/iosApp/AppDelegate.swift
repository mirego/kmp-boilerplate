import Shared
import UIKit

class AppDelegate: NSObject, UIApplicationDelegate {
    let bootstrapper = Bootstrapper()

    lazy var applicationViewModel: ApplicationViewModel = {
        bootstrapper.applicationViewModel()
    }()

    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]? = nil
    ) -> Bool {
        let bootstrap = BootstrapImpl()
        AppInitializer.initializeComponents(environment: bootstrap.environment)
        bootstrapper.doInitDependencies(bootstrap: bootstrap)

        return true
    }
}
