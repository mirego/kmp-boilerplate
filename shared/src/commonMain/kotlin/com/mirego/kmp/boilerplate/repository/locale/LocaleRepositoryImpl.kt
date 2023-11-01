package com.mirego.kmp.boilerplate.repository.locale

import com.mirego.kmp.boilerplate.bootstrap.AppInformation
import com.mirego.kmp.boilerplate.model.Locale
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Single

@Single
class LocaleRepositoryImpl(
    private val appInformation: AppInformation
) : LocaleRepository {
    override fun locale(): Flow<Locale> = appInformation.locale()
}
