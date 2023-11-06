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
            killSwitchAPIKey = KILLSWITCH_API_KEY
        ),
        androidSpecific = PlatformSpecific(
            killSwitchAPIKey = KILLSWITCH_API_KEY
        )
    ),
    PRODUCTION(
        key = "production",
        "https://api.mirego.com/graphql",
        iOSSpecific = PlatformSpecific(
            killSwitchAPIKey = KILLSWITCH_API_KEY
        ),
        androidSpecific = PlatformSpecific(
            killSwitchAPIKey = KILLSWITCH_API_KEY
        )
    )
}

data class PlatformSpecific(
    val killSwitchAPIKey: String
)

private const val KILLSWITCH_API_KEY = "Replace with your own KILLSWITCH_API_KEY"
