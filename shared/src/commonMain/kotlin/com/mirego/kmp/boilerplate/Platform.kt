package com.mirego.kmp.boilerplate

expect class Platform() {
    val system: System
    val locale: Locale
    val version: Version
}

data class System(
    val name: String,
    val version: String
)

data class Locale(
    val languageCode: String,
    val regionCode: String?
)

data class Version(
    val name: String,
    val code: Int
)
