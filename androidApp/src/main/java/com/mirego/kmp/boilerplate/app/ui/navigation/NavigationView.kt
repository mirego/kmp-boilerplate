package com.mirego.kmp.boilerplate.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.navigation.compose.rememberNavController
import com.mirego.kmp.boilerplate.viewmodel.navigation.NavigationViewModel

@Composable
fun NavigationView(navigationViewModel: NavigationViewModel, content: @Composable () -> Unit) {
    if (LocalInspectionMode.current) {
        content()
        return
    }

    val navController = rememberNavController()
    VMDNavigationView(
        navigationViewModel = navigationViewModel,
        navController = navController
    ) { startDestination ->
        BoilerplateNavHost(
            navController = navController,
            startDestination = startDestination,
            navigationViewModel = navigationViewModel,
            content = content
        )
    }
}
