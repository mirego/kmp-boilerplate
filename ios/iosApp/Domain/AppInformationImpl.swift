import Foundation
import Shared
import Trikot

class AppInformationImpl: AppInformation {
    var locale: SkieSwiftMutableStateFlow<Shared.Locale>
    let versionNumber: String
    let diskCachePath: String

    init(environmentKey: String) {
        let version = Bundle.main.infoDictionary?["CFBundleShortVersionString"] ?? ""
        let build = Bundle.main.infoDictionary?["CFBundleVersion"] ?? ""
        versionNumber = "\(version).\(build)"

        let cacheUrl = NSSearchPathForDirectoriesInDomains(.cachesDirectory, .userDomainMask, true)[0]
        diskCachePath = cacheUrl + "/\(environmentKey)/"
        
        let language = Foundation.Locale.isPreferredLanguagesFrench ? Shared.Language.french : Shared.Language.english
        let regionCode = Foundation.Locale.current.regionCode
        let currentLocale = Shared.Locale(language: language, regionCode: regionCode)
        locale = AppInformationCompanion().buildLocaleMutableStateFlow(value: currentLocale)
    }
}
