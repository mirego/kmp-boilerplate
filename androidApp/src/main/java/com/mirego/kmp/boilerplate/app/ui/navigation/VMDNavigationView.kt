package com.mirego.kmp.boilerplate.app.ui.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import com.mirego.kmp.boilerplate.viewmodel.navigation.NavigationViewModel
import com.mirego.kmp.mirego.trikot.viewmodels.declarative.navigation.VMDNavigationRoute
import com.mirego.kmp.mirego.trikot.viewmodels.declarative.navigation.VMDNavigationViewModel
import com.mirego.trikot.viewmodels.declarative.compose.extensions.observeAsState
import kotlinx.coroutines.flow.MutableStateFlow

private const val ROOT_ROUTE = "ROOT"

/**
 * For views backed by a VMDNavigationViewModel, wrap your content with VMDNavigationView to handle navigation.
 *
 * @param navigationViewModel The VMDNavigationViewModel
 * @param navController The NavHostController that controls the navigation. Note that it is possible to use custom implementations (for instance if a navigation library is used).
 * @param navOptionsBuilder The options to use for navigation
 * @param navHost The navHost containing the different navigation destinations. Note that it is possible to use custom implementations (for instance if a navigation library is used).
 */
@Composable
fun <T : VMDNavigationRoute> VMDNavigationView(
    navigationViewModel: VMDNavigationViewModel<T>,
    navController: NavHostController,
    navOptionsBuilder: NavOptionsBuilder.() -> Unit = {
        launchSingleTop = true
    },
    navHost: @Composable (startDestination: String) -> Unit
) {
    navHost(startDestination = ROOT_ROUTE)

    NavigationHandler(
        navigationViewModel = navigationViewModel,
        navController = navController,
        navOptionsBuilder = navOptionsBuilder
    )
}

@Composable
private fun <T : VMDNavigationRoute> NavigationHandler(
    navigationViewModel: VMDNavigationViewModel<T>,
    navController: NavHostController,
    navOptionsBuilder: NavOptionsBuilder.() -> Unit
) {
    val navigationRoute by navigationViewModel.observeAsState(navigationViewModel::navigationRoute, navigationViewModel.navigationRoute)
    val currentRoute = navController.currentDestination

    when (val navigation = getNavigation(navigationRoute, currentRoute)) {
        is VMDNavigation.POP -> navController.popBackStack()
        is VMDNavigation.LAUNCH -> navController.navigate(
            route = navigation.destinationRoute,
            builder = navOptionsBuilder
        )
        is VMDNavigation.NONE -> Unit
    }
    val lifecycleEventState = MutableStateFlow(Lifecycle.Event.ON_START)
    val observer = LifecycleEventObserver { _, event ->
        lifecycleEventState.value = event
    }
    val lifecycleOwner = LocalLifecycleOwner.current

    lifecycleOwner.lifecycle.addObserver(observer)

    DisposableEffect(lifecycleOwner) {
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
    val state = lifecycleEventState.collectAsState()

    key(state.value) {
        BackHandler(
            enabled = navigationRoute != null && (navigationRoute?.viewModel as? NavigationViewModel)?.navigationRoute == null
        ) {
            navigationRoute?.resetBlock?.invoke()
        }
    }
}

private fun getNavigation(
    navigationRoute: VMDNavigationRoute?,
    currentNavDestination: NavDestination?
): VMDNavigation = when {
    navigationRoute == null && !currentNavDestination.isRoot -> VMDNavigation.POP
    navigationRoute != null && currentNavDestination.isRoot -> VMDNavigation.LAUNCH(navigationRoute.name)
    else -> VMDNavigation.NONE
}

private val NavDestination?.isRoot: Boolean
    get() = this == null || this.route == null || this.route == ROOT_ROUTE

private sealed class VMDNavigation {
    object NONE : VMDNavigation()
    object POP : VMDNavigation()
    data class LAUNCH(
        val destinationRoute: String
    ) : VMDNavigation()
}
