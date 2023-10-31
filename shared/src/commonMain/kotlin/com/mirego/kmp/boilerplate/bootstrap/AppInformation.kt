package com.mirego.kmp.boilerplate.bootstrap

import com.mirego.kmp.boilerplate.model.Locale
import kotlinx.coroutines.flow.MutableStateFlow

interface AppInformation {
    companion object {
        fun buildLocaleMutableStateFlow(value: Locale) = MutableStateFlow(value)
    }

    val locale: MutableStateFlow<Locale>
    val versionNumber: String
    val diskCachePath: String
}
