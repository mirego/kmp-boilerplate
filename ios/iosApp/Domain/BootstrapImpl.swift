import Shared
import SwiftUI

final class BootstrapImpl: Bootstrap {
    let appInformation: AppInformation
    let environment = AppEnvironment.current

    init() {
        appInformation = AppInformationImpl(environmentKey: environment.key)
    }
}
