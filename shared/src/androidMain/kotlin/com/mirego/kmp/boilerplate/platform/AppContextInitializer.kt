package com.mirego.kmp.boilerplate.platform

import android.content.Context
import androidx.startup.Initializer
import com.mirego.konnectivity.KonnectivityInitializer

internal lateinit var appContext: Context

class AppContextInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        appContext = context
    }

    override fun dependencies() = listOf(
        KonnectivityInitializer::class.java
    )
}
