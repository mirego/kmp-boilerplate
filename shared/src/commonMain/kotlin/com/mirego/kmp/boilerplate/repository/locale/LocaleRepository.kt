package com.mirego.kmp.boilerplate.repository.locale

import com.mirego.kmp.boilerplate.model.Locale
import kotlinx.coroutines.flow.Flow

interface LocaleRepository {
    fun locale(): Flow<Locale>
}
