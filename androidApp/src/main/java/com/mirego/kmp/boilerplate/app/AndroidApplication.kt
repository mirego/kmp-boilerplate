package com.mirego.kmp.boilerplate.app

import android.app.Application
import android.content.res.Configuration
import com.mirego.kmp.boilerplate.app.bootstrap.AndroidBootstrap
import com.mirego.kmp.boilerplate.bootstrap.Bootstrapper

class AndroidApplication : Application() {
    private lateinit var bootstrap: AndroidBootstrap
    val bootstrapper = Bootstrapper()

    override fun onCreate() {
        super.onCreate()
        bootstrap = AndroidBootstrap(this)
        bootstrapper.initDependencies(bootstrap)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        bootstrap.appInformation.updateLocale()
    }
}
