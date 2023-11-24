package com.mirego.kmp.boilerplate.bootstrap

import platform.Foundation.NSLocale
import platform.Foundation.preferredLanguages

actual object LocaleUtils {
    actual fun supportedLanguageCode(): String {
        val preferredLanguage: String? = NSLocale.preferredLanguages.firstOrNull() as? String
        return if (preferredLanguage?.contains("fr") == true) {
            "fr"
        } else {
            "en"
        }
    }
}
