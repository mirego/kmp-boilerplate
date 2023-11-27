package com.mirego.kmp.boilerplate.bootstrap

import com.apollographql.apollo3.api.http.HttpRequest
import com.apollographql.apollo3.api.http.HttpResponse
import com.apollographql.apollo3.network.http.HttpInterceptor
import com.apollographql.apollo3.network.http.HttpInterceptorChain
import com.mirego.kmp.boilerplate.repository.locale.LocaleRepository
import kotlinx.coroutines.flow.first
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LocaleHeaderInterceptor : HttpInterceptor, KoinComponent {
    private val localeRepository: LocaleRepository by inject()

    override suspend fun intercept(request: HttpRequest, chain: HttpInterceptorChain): HttpResponse {
        val locale = localeRepository.locale().first()

        return chain.proceed(
            request
                .newBuilder()
                .addHeader("X-Accept-Language", locale.language.toLangCode())
                .build()
        )
    }
}
