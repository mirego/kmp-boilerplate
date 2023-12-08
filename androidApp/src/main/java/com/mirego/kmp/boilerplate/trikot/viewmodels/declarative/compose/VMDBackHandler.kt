package com.mirego.kmp.boilerplate.trikot.viewmodels.declarative.compose

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import com.mirego.kmp.boilerplate.viewmodel.navigation.DemoNavigationManager

@Composable
fun VMDBackHandler(
    navController: NavHostController,
    navigationManager: DemoNavigationManager,
    rootName: String
) {
    val backStackEntry by navController.currentBackStackEntryFlow.collectAsState(initial = null)
    BackHandler(enabled = backStackEntry?.destination?.route != rootName) {
        navigationManager.pop()
    }
}
