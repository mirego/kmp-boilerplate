package com.mirego.kmp.boilerplate.app

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.mirego.killswitch.AndroidKillswitch
import com.mirego.killswitch.KillswitchException
import com.mirego.kmp.boilerplate.app.ui.application.ApplicationView
import com.mirego.kmp.boilerplate.bootstrap.Bootstrapper
import com.mirego.kmp.boilerplate.trikot.viewmodels.declarative.compose.getInitialViewModel
import com.mirego.kmp.boilerplate.viewmodel.application.ApplicationViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val bootstrapper: Bootstrapper
        get() = (applicationContext as AndroidApplication).bootstrapper

    private val viewModel: ApplicationViewModel by lazy {
        getInitialViewModel {
            bootstrapper.applicationViewModel()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            ApplicationView(applicationViewModel = viewModel)
        }

        lifecycleScope.launch {
            try {
                AndroidKillswitch.showDialog(
                    viewData = AndroidKillswitch.engage(
                        key = bootstrapper.bootstrap.environment.androidSpecific.killSwitchAPIKey,
                        context = this@MainActivity,
                        url = bootstrapper.bootstrap.environment.androidSpecific.killSwitchUrl
                    ),
                    activity = this@MainActivity,
                    themeResId = android.R.style.Theme_DeviceDefault_Light_Dialog_Alert
                )
            } catch (e: KillswitchException) {
                Log.e(TAG, "Killswitch exception", e)
            }
        }
    }
}
