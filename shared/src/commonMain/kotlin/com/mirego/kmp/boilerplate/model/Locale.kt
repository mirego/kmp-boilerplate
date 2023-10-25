package com.mirego.kmp.boilerplate.model

data class Locale(
    val language: Language,
    val regionCode: String?
)

enum class Language {
    ENGLISH,
    FRENCH;

    fun toLangCode() = when (this) {
        ENGLISH -> "en"
        FRENCH -> "fr"
    }
}
