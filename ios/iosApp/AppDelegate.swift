import Shared
import UIKit

class AppDelegate: NSObject, UIApplicationDelegate {

    var viewModelFactory: ViewModelFactory!

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {

        let boostrapper = AppBootstrapper()
        viewModelFactory = boostrapper.viewModelFactory

        return true
    }
}
