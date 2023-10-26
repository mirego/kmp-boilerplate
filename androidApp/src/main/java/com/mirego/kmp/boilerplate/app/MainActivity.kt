package com.mirego.kmp.boilerplate.app

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.mirego.kmp.boilerplate.app.ui.application.ApplicationView
import com.mirego.kmp.boilerplate.bootstrap.Bootstrapper
import com.mirego.kmp.boilerplate.trikot.viewmodels.declarative.compose.getInitialViewModel
import com.mirego.kmp.boilerplate.viewmodel.application.ApplicationViewModel

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
    }
}
