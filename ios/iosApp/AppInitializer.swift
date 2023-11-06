import FirebaseCore
import Foundation
import Kingfisher
import Shared
import Trikot

enum AppInitializer {
    static func initializeComponents(environment _: AppEnvironment) {
        initializeFirebase()
        initializeCommon()
        initializeKingfisher()
        inititalizeKillSwitch()
    }

    private static func initializeFirebase() {
        guard
            let filePath = Bundle.main.path(forResource: "ToReplace-GoogleService-Info", ofType: "plist"),
            let firebaseOptions = FirebaseOptions(contentsOfFile: filePath) else {
            return
        }

        FirebaseApp.configure(options: firebaseOptions)

        let firebaseAnalyticsService = AnalyticsServiceImpl()
        #if DEBUG
            firebaseAnalyticsService.isEnabled = false
        #else
            firebaseAnalyticsService.isEnabled = true
        #endif

        SharedAnalyticsConfiguration().analyticsManager = firebaseAnalyticsService
    }

    private static func initializeCommon() {
        TrikotKword.shared.setCurrentLanguage(Foundation.Locale.isPreferredLanguagesFrench ? "fr" : "en")
        TrikotViewModelDeclarative.shared.initialize(
            imageProvider: ImageProvider(),
            spanStyleProvider: DefaultSpanStyleProvider()
        )
    }

    private static func initializeKingfisher() {
        ImageCache.default.diskStorage.config.sizeLimit = 500 * 1_024 * 1_024 // 500 MB
    }
    
    private static func inititalizeKillSwitch() {
        Task {
            do {
                let viewData = try await IOSKillswitch().engage(
                    key: AppEnvironment.current.iOSSpecific.killSwitchAPIKey,
                    url: AppEnvironment.current.iOSSpecific.killSwitchUrl
                )
                DispatchQueue.main.async {
                    IOSKillswitch().showDialog(viewData: viewData)
                }
            } catch {
                print("Killswitch error: \(error)")
            }
        }
    }
}
