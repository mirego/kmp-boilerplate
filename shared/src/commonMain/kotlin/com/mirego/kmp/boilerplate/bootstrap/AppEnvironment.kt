package com.mirego.kmp.boilerplate.bootstrap

enum class AppEnvironment(
    val key: String,
    val graphQlApiUrl: String
) {
    DEV(
        key = "dev",
        "https://api-qa.mirego.com/graphql"
    ),
    PRODUCTION(
        key = "production",
        "https://api.mirego.com/graphql"
    )
}
