package com.mirego.kmp.boilerplate.app

import android.app.Application
import com.mirego.kmp.boilerplate.app.bootstrap.AndroidBootstrap
import com.mirego.kmp.boilerplate.bootstrap.Bootstrapper

class AndroidApplication : Application() {
    val bootstrapper = Bootstrapper()

    override fun onCreate() {
        super.onCreate()
        bootstrapper.initDependencies(AndroidBootstrap(this))
    }
}
