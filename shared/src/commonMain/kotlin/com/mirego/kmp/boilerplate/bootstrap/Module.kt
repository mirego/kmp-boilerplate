package com.mirego.kmp.boilerplate.bootstrap

import com.apollographql.apollo3.ApolloClient
import com.mirego.kmp.boilerplate.SharedModule
import com.mirego.trikot.kword.I18N
import com.mirego.trikot.kword.KWord
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import org.koin.core.KoinApplication
import org.koin.core.module.Module
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module
import org.koin.ksp.generated.module

fun KoinApplication.configureKoin(bootstrap: Bootstrap) {
    modules(
        generalModule(bootstrap),
        SharedModule().module
    )
}

@OptIn(ExperimentalSerializationApi::class)
fun buildJson() = Json {
    explicitNulls = false
    isLenient = true
    ignoreUnknownKeys = true
}

fun generalModule(bootstrap: Bootstrap): Module {
    return module {
        val environment = bootstrap.environment
        single { createApolloClientBuilder(environment).build() }
        single<I18N> { KWord }
        single { bootstrap.environment }
        single { bootstrap.appInformation }
        single { bootstrap.appInformation.localeData.language }
        single(StringQualifier(ModuleQualifier.REGION_CODE)) { bootstrap.appInformation.localeData.regionCode }
        single(StringQualifier(ModuleQualifier.DISK_CACHE_PATH)) { bootstrap.appInformation.diskCachePath + "/${bootstrap.appInformation.localeData.language.toLangCode()}" }
        single { buildJson() }
    }
}

private fun createApolloClientBuilder(appEnvironment: AppEnvironment): ApolloClient.Builder =
    ApolloClient.Builder()
        .serverUrl(appEnvironment.graphQlApiUrl)
        .autoPersistedQueries()


object ModuleQualifier {
    const val DISK_CACHE_PATH = "diskCachePath"
    const val REGION_CODE = "regionCode"
}
