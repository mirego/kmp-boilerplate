package com.mirego.kmp.boilerplate.bootstrap

import com.mirego.kmp.boilerplate.model.Locale

interface AppInformation {
    val locale: Locale
    val versionNumber: String
    val diskCachePath: String
}
