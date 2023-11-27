package com.mirego.kmp.boilerplate.bootstrap

enum class AppEnvironment(
    val key: String,
    val graphQlApiUrl: String,
    val iOSSpecific: PlatformSpecific,
    val androidSpecific: PlatformSpecific
) {
    DEV(
        key = "dev",
        "https://api.mirego.com/graphql",
        iOSSpecific = PlatformSpecific(
            killSwitchAPIKey = "", // Replace with your own killSwitchAPIKey
            killSwitchUrl = "https://killswitch.mirego.com/killswitch"
        ),
        androidSpecific = PlatformSpecific(
            killSwitchAPIKey = "", // Replace with your own killSwitchAPIKey
            killSwitchUrl = "https://killswitch.mirego.com/killswitch"
        )
    ),
    PRODUCTION(
        key = "production",
        "https://api.mirego.com/graphql",
        iOSSpecific = PlatformSpecific(
            killSwitchAPIKey = "", // Replace with your own killSwitchAPIKey
            killSwitchUrl = "https://killswitch.mirego.com/killswitch"
        ),
        androidSpecific = PlatformSpecific(
            killSwitchAPIKey = "", // Replace with your own killSwitchAPIKey
            killSwitchUrl = "https://killswitch.mirego.com/killswitch"
        )
    )
}

data class PlatformSpecific(
    val killSwitchAPIKey: String,
    val killSwitchUrl: String
)
