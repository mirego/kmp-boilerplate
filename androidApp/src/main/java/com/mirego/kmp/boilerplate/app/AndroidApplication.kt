package com.mirego.kmp.boilerplate.app

import android.app.Application
import android.content.res.Configuration
import com.mirego.kmp.boilerplate.app.bootstrap.AndroidBootstrap
import com.mirego.kmp.boilerplate.bootstrap.Bootstrapper

class AndroidApplication : Application() {
    lateinit var bootstrapper: Bootstrapper

    override fun onCreate() {
        super.onCreate()
        bootstrapper = Bootstrapper(AndroidBootstrap(this))
        bootstrapper.initDependencies()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        (bootstrapper.bootstrap as? AndroidBootstrap)?.appInformation?.updateLocale()
    }
}
