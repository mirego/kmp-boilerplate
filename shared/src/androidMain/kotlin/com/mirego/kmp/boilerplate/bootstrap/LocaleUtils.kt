package com.mirego.kmp.boilerplate.bootstrap

import java.util.Locale

actual object LocaleUtils {
    actual fun supportedLanguageCode() = if (Locale.getDefault().language.lowercase() == "fr") {
        "fr"
    } else {
        "en"
    }
}
