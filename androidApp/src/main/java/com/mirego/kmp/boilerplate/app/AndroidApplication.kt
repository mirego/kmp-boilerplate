package com.mirego.kmp.boilerplate.app

import android.app.Application
import android.content.res.Configuration
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.distribute.Distribute
import com.microsoft.appcenter.distribute.UpdateTrack
import com.mirego.kmp.boilerplate.R
import com.mirego.kmp.boilerplate.app.bootstrap.AndroidBootstrap
import com.mirego.kmp.boilerplate.bootstrap.Bootstrapper

class AndroidApplication : Application() {
    lateinit var bootstrapper: Bootstrapper

    override fun onCreate() {
        super.onCreate()
        bootstrapper = Bootstrapper(AndroidBootstrap(this))
        bootstrapper.initDependencies()

        if (getString(R.string.appcenter_app_secret).isNotBlank()) {
            Distribute.setUpdateTrack(UpdateTrack.PRIVATE)
            AppCenter.start(this, getString(R.string.appcenter_app_secret), Distribute::class.java)
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        (bootstrapper.bootstrap as? AndroidBootstrap)?.appInformation?.updateLocale()
    }
}
