package com.mirego.kmp.boilerplate.app

import android.os.Bundle
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.mirego.kmp.boilerplate.app.ui.application.ApplicationView
import com.mirego.kmp.boilerplate.bootstrap.Bootstrapper
import com.mirego.kmp.boilerplate.viewmodel.application.ApplicationViewModel

class MainActivity : AppCompatActivity() {
    private val bootstrapper: Bootstrapper
        get() = (applicationContext as AndroidApplication).bootstrapper

    private val viewModel: ApplicationViewModel by lazy {
        bootstrapper.applicationViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                lightScrim = android.graphics.Color.TRANSPARENT,
                darkScrim = android.graphics.Color.TRANSPARENT,
                detectDarkMode = { true },
            ),
            navigationBarStyle = SystemBarStyle.dark(android.graphics.Color.TRANSPARENT),
        )
        setContent {
            ApplicationView(applicationViewModel = viewModel)
        }
    }
}
