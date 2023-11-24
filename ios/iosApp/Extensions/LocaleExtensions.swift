import Foundation

extension Foundation.Locale {
    static var isPreferredLanguagesFrench: Bool {
        if let language = Locale.preferredLanguages.first, language.lowercased().contains("fr") {
            return true
        } else {
            return false
        }
    }

    static var preferredLocale: Locale {
        isPreferredLanguagesFrench ? Locale(identifier: "fr-CA") : Locale(identifier: "en-CA")
    }
}
