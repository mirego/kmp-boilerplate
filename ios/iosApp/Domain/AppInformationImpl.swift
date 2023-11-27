import Foundation
import Shared
import Trikot

class AppInformationImpl: AppInformation {
    private let flowProvider: FlowProvider<Shared.Locale> = FlowProvider()
    private lazy var localeStateFlow: ConcreteMutableStateFlow<Shared.Locale> = {
        let language = LocaleUtils().supportedLanguageCode() == "fr" ? Shared.Language.french : Shared.Language.english
        let regionCode = Foundation.Locale.current.regionCode
        let currentLocale = Shared.Locale(language: language, regionCode: regionCode)
        return flowProvider.mutableStateFlow(initialValue: currentLocale)
    }()

    let versionNumber: String
    let diskCachePath: String

    init(environmentKey: String) {
        let version = Bundle.main.infoDictionary?["CFBundleShortVersionString"] ?? ""
        let build = Bundle.main.infoDictionary?["CFBundleVersion"] ?? ""
        versionNumber = "\(version).\(build)"

        let cacheUrl = NSSearchPathForDirectoriesInDomains(.cachesDirectory, .userDomainMask, true)[0]
        diskCachePath = cacheUrl + "/\(environmentKey)/"
    }

    func locale() -> ConcreteFlow<Shared.Locale> {
        localeStateFlow
    }
}
