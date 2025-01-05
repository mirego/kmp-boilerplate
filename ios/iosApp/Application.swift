import Shared

enum Application {
    static let koin = IOSKoin(bootstrapper: Bootstrapper(bootstrap: BootstrapImpl()))
}
