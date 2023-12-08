package com.mirego.kmp.boilerplate.app.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import com.mirego.compose.utils.extensions.currentActivity
import com.mirego.kmp.boilerplate.app.ui.dialogs.DialogView
import com.mirego.kmp.boilerplate.app.ui.screens.Screen1View
import com.mirego.kmp.boilerplate.app.ui.screens.Screen2View
import com.mirego.kmp.boilerplate.app.ui.screens.Screen3View
import com.mirego.kmp.boilerplate.trikot.viewmodels.declarative.compose.VMDBackHandler
import com.mirego.kmp.boilerplate.trikot.viewmodels.declarative.compose.VMDNavigationManagerListenerImpl
import com.mirego.kmp.boilerplate.trikot.viewmodels.declarative.compose.findRoute
import com.mirego.kmp.boilerplate.trikot.viewmodels.declarative.compose.vmdNavArguments
import com.mirego.kmp.boilerplate.trikot.viewmodels.declarative.compose.vmdNavRoute
import com.mirego.kmp.boilerplate.trikot.viewmodels.declarative.compose.vmdViewModel
import com.mirego.kmp.boilerplate.viewmodel.navigation.DemoNavigationManager
import com.mirego.kmp.boilerplate.viewmodel.navigation.DemoNavigationRouteName

private const val rootRoute = "root"

@Composable
fun DemoNavHost(
    navController: NavHostController,
    navigationManager: DemoNavigationManager,
    enterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition,
    exitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition,
    popEnterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition,
    popExitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition,
    rootContent: @Composable () -> Unit
) {
    val localUriHandler = LocalUriHandler.current
    val localActivity = LocalContext.currentActivity

    LaunchedEffect(key1 = navigationManager, key2 = navController) {
        navigationManager.listener = VMDNavigationManagerListenerImpl(navController)
        navigationManager.actionListener =
            DemoActionNavigationListener(
                localUriHandler = localUriHandler,
                localActivity = localActivity
            )
    }

    NavHost(
        navController = navController,
        startDestination = rootRoute,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition
    ) {
        composable(rootRoute) {
            rootContent()
        }
        DemoNavigationRouteName.entries.forEach { routeName ->
            val content: @Composable (NavBackStackEntry) -> Unit = { backStackEntry ->
                when (routeName) {
                    DemoNavigationRouteName.SCREEN1 -> {
                        val viewModel = vmdViewModel {
                            navigationManager.createScreen1(navigationManager.findRoute(backStackEntry))
                        }
                        Screen1View(viewModel)
                    }

                    DemoNavigationRouteName.SCREEN2 -> {
                        val viewModel = vmdViewModel {
                            navigationManager.createScreen2(navigationManager.findRoute(backStackEntry))
                        }
                        Screen2View(viewModel)
                    }

                    DemoNavigationRouteName.SCREEN3 -> {
                        val viewModel = vmdViewModel {
                            navigationManager.createScreen3(navigationManager.findRoute(backStackEntry))
                        }
                        Screen3View(viewModel)
                    }

                    DemoNavigationRouteName.DIALOG -> {
                        val viewModel = vmdViewModel {
                            navigationManager.createDialog(navigationManager.findRoute(backStackEntry))
                        }
                        DialogView(viewModel)
                    }
                }
            }

            if (routeName.isDialog) {
                dialog(
                    route = vmdNavRoute(routeName.name),
                    arguments = vmdNavArguments
                ) {
                    content(it)
                }
            } else {
                composable(
                    route = vmdNavRoute(routeName.name),
                    arguments = vmdNavArguments
                ) {
                    content(it)
                }
            }
        }
    }

    VMDBackHandler(
        navController = navController,
        navigationManager = navigationManager,
        rootName = rootRoute
    )
}


private val DemoNavigationRouteName.isDialog: Boolean
    get() = this == DemoNavigationRouteName.DIALOG
