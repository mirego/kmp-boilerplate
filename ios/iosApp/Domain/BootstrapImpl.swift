import Shared
import SwiftUI
import Trikot

final class BootstrapImpl: Bootstrap {
    let appInformation: AppInformation
    let environment = AppEnvironment.current

    init() {
        appInformation = AppInformationImpl(environmentKey: environment.key)
        
        let firebaseAnalyticsService = AnalyticsServiceImpl()
        #if DEBUG
            firebaseAnalyticsService.isEnabled = false
        #else
           firebaseAnalyticsService.isEnabled = true
        #endif
        
        SharedAnalyticsConfiguration().analyticsManager = firebaseAnalyticsService
    }
}
