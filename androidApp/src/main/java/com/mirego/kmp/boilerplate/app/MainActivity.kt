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
import com.mirego.kmp.boilerplate.bootstrap.AppEnvironment
import com.mirego.kmp.boilerplate.bootstrap.Bootstrapper
import com.mirego.kmp.boilerplate.trikot.viewmodels.declarative.compose.getInitialViewModel
import com.mirego.kmp.boilerplate.utils.Const
import com.mirego.kmp.boilerplate.viewmodel.application.ApplicationViewModel
import kotlinx.coroutines.launch
import org.koin.core.component.get

class MainActivity : AppCompatActivity() {
    private val bootstrapper: Bootstrapper
        get() = (applicationContext as AndroidApplication).bootstrapper

    private val viewModel: ApplicationViewModel by lazy {
        getInitialViewModel {
            bootstrapper.applicationViewModel()
        }
    }

    private val appEnvironment: AppEnvironment by lazy {
        bootstrapper.get()
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
                    AndroidKillswitch.engage(appEnvironment.androidSpecific.killSwitchAPIKey, this@MainActivity, Const.KILLSWITCH_URL), // TODO use current appEnvironment
                    this@MainActivity,
                    android.R.style.Theme_DeviceDefault_Light_Dialog_Alert
                )
            } catch (e: KillswitchException) {
                Log.e(TAG, "Killswitch exception", e)
            }
        }
    }
}
