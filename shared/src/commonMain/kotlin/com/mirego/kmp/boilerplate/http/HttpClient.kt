package com.mirego.kmp.boilerplate.http

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.annotation.Single

expect fun sharedHttpClient(config: HttpClientConfig<*>.() -> Unit = {}): HttpClient

@Single
class HttpNotAuthenticated(jsonParser: Json) {
    val httpClient: HttpClient = sharedHttpClient {
        install(HttpTimeout) {
            requestTimeoutMillis = 30000
        }
        install(ContentNegotiation) {
            json(jsonParser)
        }
    }
}
