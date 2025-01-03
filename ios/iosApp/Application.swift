import Shared
import Trikot

enum Application {
    static let koin = IOSKoin(bootstrapper: Bootstrapper(bootstrap: BootstrapImpl()))
}
