package com.mirego.kmp.boilerplate.bootstrap

enum class AppEnvironment(
    val key: String
) {
    DEV(
        key = "dev"
    ),
    PRODUCTION(
        key = "production",
    )
}
