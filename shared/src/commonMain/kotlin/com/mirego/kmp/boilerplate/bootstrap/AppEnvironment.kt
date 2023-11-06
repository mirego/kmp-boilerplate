package com.mirego.kmp.boilerplate.bootstrap

enum class AppEnvironment(
    val key: String,
    val graphQlApiUrl: String,
    val iOSSpecific: PlatformSpecific,
    val androidSpecific: PlatformSpecific,
) {
    DEV(
        key = "dev",
        "https://api.mirego.com/graphql",
        iOSSpecific = PlatformSpecific(
            killSwitchAPIKey = "Replace with your own killSwitchAPIKey"
        ),
        androidSpecific = PlatformSpecific(
            killSwitchAPIKey = "Replace with your own killSwitchAPIKey"
        )
    ),
    PRODUCTION(
        key = "production",
        "https://api.mirego.com/graphql",
        iOSSpecific = PlatformSpecific(
            killSwitchAPIKey = "Replace with your own killSwitchAPIKey"
        ),
        androidSpecific = PlatformSpecific(
            killSwitchAPIKey = "Replace with your own killSwitchAPIKey"
        )
    )
}

data class PlatformSpecific(
    val killSwitchAPIKey: String
)
