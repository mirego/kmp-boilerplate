import Shared
import SwiftUI
import Trikot

final class BootstrapImpl: Bootstrap {
    let appInformation: AppInformation
    let environment = AppEnvironment.current

    init() {
        appInformation = AppInformationImpl(environmentKey: environment.key)
    }
}
