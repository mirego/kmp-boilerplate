package com.mirego.kmp.boilerplate.app.ui.navigation

import android.app.Activity
import android.provider.Settings
import androidx.compose.ui.platform.UriHandler
import com.mirego.compose.utils.extensions.navigateToSettings
import com.mirego.kmp.boilerplate.viewmodel.navigation.NavigationAction
import com.mirego.kmp.boilerplate.viewmodel.navigation.vmd.VMDActionNavigationListener

class DemoActionNavigationListener(
    private val localUriHandler: UriHandler,
    private val localActivity: Activity?
) : VMDActionNavigationListener<NavigationAction>() {

    override fun handleAction(action: NavigationAction) {
        when (action) {
            is NavigationAction.ExternalUrl -> {
                localUriHandler.openUri(action.url)
            }

            NavigationAction.OpenSettings -> {
                localActivity?.navigateToSettings(Settings.ACTION_SETTINGS)
            }
        }
    }
}
