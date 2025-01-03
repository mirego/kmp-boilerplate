package com.mirego.kmp.boilerplate.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalInspectionMode
import com.mirego.kmp.boilerplate.viewmodel.navigation.NavigationManager

@Composable
fun NavigationView(navigationManager: NavigationManager, content: @Composable () -> Unit) {
    if (LocalInspectionMode.current) {
        content()
        return
    }

    val navController = rememberNavController(navigationManager)
    BoilerplateNavHost(
        navController = navController,
        navigationManager = navigationManager,
        rootContent = content
    )
}
