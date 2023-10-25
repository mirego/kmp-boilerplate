package com.mirego.kmp.boilerplate.model

data class Locale(
    val language: Language,
    val regionCode: String?
)

enum class Language {
    ENGLISH,
    FRENCH;

    fun toMulesoftLang() = when (this) {
        ENGLISH -> "en-CA"
        FRENCH -> "fr-CA"
    }

    fun toLangCode() = when (this) {
        ENGLISH -> "en"
        FRENCH -> "fr"
    }
}
