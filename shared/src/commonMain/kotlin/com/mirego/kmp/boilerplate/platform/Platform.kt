package com.mirego.kmp.boilerplate.platform

expect fun Platform(): Platform

interface Platform {
    val system: System
    val locale: Locale
    val version: Version
}

data class Locale(
    val languageCode: String,
    val regionCode: String?
)

data class System(
    val name: String,
    val version: String
)

data class Version(
    val name: String,
    val code: Int
)
