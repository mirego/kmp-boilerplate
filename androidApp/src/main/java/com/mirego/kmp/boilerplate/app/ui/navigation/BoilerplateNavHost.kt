package com.mirego.kmp.boilerplate.app.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mirego.kmp.boilerplate.app.ui.projectdetails.ProjectDetailsView
import com.mirego.kmp.boilerplate.viewmodel.navigation.NavigationManager
import com.mirego.kmp.boilerplate.viewmodel.navigation.NavigationRouteName
import com.mirego.kmp.boilerplate.viewmodel.projectdetails.ProjectDetailsViewModel
import com.mirego.pilot.navigation.compose.PilotNavControllerNavigationListener
import com.mirego.pilot.navigation.compose.findRoute
import org.koin.androidx.compose.koinViewModel

internal const val ROOT_ROUTE = "root"

@Composable
fun BoilerplateNavHost(
    navController: NavHostController,
    navigationManager: NavigationManager,
    content: @Composable () -> Unit
) {
    LaunchedEffect(navigationManager, navController) {
        navigationManager.listener = PilotNavControllerNavigationListener(navController)
        navigationManager.actionListener = ActionNavigationListener()
    }

    NavHost(
        navController = navController,
        startDestination = ROOT_ROUTE,
        enterTransition = { fadeIn(tween(500)) },
        exitTransition = { fadeOut(tween(500)) }
    ) {
        composable(ROOT_ROUTE) {
            content()
        }
        composable(
            route = NavigationRouteName.PROJECT_DETAILS.name
        ) {
            NavigableContent(NavigationRouteName.PROJECT_DETAILS, navigationManager)
        }
    }
}

@Composable
private fun NavigableContent(route: NavigationRouteName, navigationManager: NavigationManager): @Composable (NavBackStackEntry) -> Unit {
    val content: @Composable (NavBackStackEntry) -> Unit = { backStackEntry ->
        Box(modifier = Modifier.fillMaxSize()) {
            when (route) {
                NavigationRouteName.PROJECT_DETAILS -> ProjectDetailsView(
                    projectDetailsViewModel = koinViewModel { ProjectDetailsViewModel.parameters(navigationManager, navigationManager.findRoute(backStackEntry)) }
                )
            }
        }
    }
    return content
}
