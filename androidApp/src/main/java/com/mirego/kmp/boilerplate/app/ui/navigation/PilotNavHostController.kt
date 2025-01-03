@file:Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package com.mirego.kmp.boilerplate.app.ui.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.ComposeNavGraphNavigator
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.DialogNavigator
import com.mirego.pilot.navigation.PilotNavigationManager
import com.mirego.pilot.navigation.PilotNavigationRoute

/**
 * Creates a [NavHostController] that is remembered across compositions and config changes.
 * We need this because if the navigationManager changes, we want to create a new [NavHostController], otherwise
 * it will crash due to a de-sync between the navigation manager and the [NavHostController].
 */
@Composable
fun rememberNavController(navigationManager: PilotNavigationManager<out PilotNavigationRoute, out Any>): NavHostController {
    val context = LocalContext.current
    return rememberSaveable(navigationManager, saver = NavControllerSaver(context, navigationManager)) {
        createNavController(context)
    }
}

private fun createNavController(context: Context) =
    NavHostController(context).apply {
        navigatorProvider.addNavigator(ComposeNavGraphNavigator(navigatorProvider))
        navigatorProvider.addNavigator(ComposeNavigator())
        navigatorProvider.addNavigator(DialogNavigator())
    }

@Suppress("FunctionName")
private fun NavControllerSaver(context: Context, navigationManager: PilotNavigationManager<out PilotNavigationRoute, out Any>): Saver<NavHostController, *> =
    Saver(
        save = { it.saveState() },
        restore = {
            createNavController(context).apply {
                if (navigationManager.currentRoutes().isNotEmpty()) {
                    restoreState(it)
                }
            }
        },
    )
