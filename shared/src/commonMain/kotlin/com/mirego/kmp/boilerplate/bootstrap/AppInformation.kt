package com.mirego.kmp.boilerplate.bootstrap

import com.mirego.kmp.boilerplate.model.LocaleData

interface AppInformation {
    val localeData: LocaleData
    val versionNumber: String
    val diskCachePath: String
}
