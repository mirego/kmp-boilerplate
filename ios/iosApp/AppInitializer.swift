import Foundation
import Kingfisher
import Shared
import Trikot

enum AppInitializer {
    static func initializeComponents(environment: AppEnvironment) {
        initializeCommon()
        initializeKingfisher()
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
}
