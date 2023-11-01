package com.mirego.kmp.boilerplate.bootstrap

import com.mirego.kmp.boilerplate.model.Locale
import com.mirego.kmp.boilerplate.utils.ConcreteFlow

interface AppInformation {
    fun locale(): ConcreteFlow<Locale>
    val versionNumber: String
    val diskCachePath: String
}
