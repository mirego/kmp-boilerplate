package com.mirego.kmp.boilerplate.app.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mirego.kmp.boilerplate.app.ui.projectdetails.ProjectDetailsView
import com.mirego.kmp.boilerplate.viewmodel.navigation.NavigationRoute
import com.mirego.kmp.boilerplate.viewmodel.navigation.NavigationViewModel

@Composable
fun BoilerplateNavHost(
    navController: NavHostController,
    startDestination: String,
    navigationViewModel: NavigationViewModel,
    content: @Composable () -> Unit
) = NavHost(
    navController = navController,
    startDestination = startDestination,
    enterTransition = { fadeIn(tween(500)) },
    exitTransition = { fadeOut(tween(500)) }
) {
    composable(startDestination) {
        content()
    }
    composable(NavigationRoute.ProjectDetails.NAME) {
        NavigableContent(navigationViewModel)
    }
}

@Composable
private fun NavigableContent(navigationViewModel: NavigationViewModel) {
    Box(modifier = Modifier.fillMaxSize()) {
        VMDNavigableContent(navigationViewModel = navigationViewModel) { navigationRoute ->
            when (navigationRoute) {
                is NavigationRoute.ProjectDetails -> ProjectDetailsView(projectDetailsViewModel = navigationRoute.viewModel)
            }
        }
    }
}
