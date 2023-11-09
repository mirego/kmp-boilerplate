package com.mirego.kmp.boilerplate

import android.content.Context
import androidx.startup.Initializer

internal lateinit var appContext: Context

class BootstrapperInitializer : Initializer<Bootstrapper> {
    override fun create(context: Context): Bootstrapper {
        appContext = context.applicationContext
        return AppBootstrapper()
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}
