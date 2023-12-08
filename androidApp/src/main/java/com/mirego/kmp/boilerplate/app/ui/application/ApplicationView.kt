@file:Suppress("AnimatedContentLabel")

package com.mirego.kmp.boilerplate.app.ui.application

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.primarySurface
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.mirego.compose.utils.navigationBarPadding
import com.mirego.kmp.boilerplate.app.ui.navigation.DemoNavHost
import com.mirego.kmp.boilerplate.app.ui.navigation.DemoTabs
import com.mirego.kmp.boilerplate.app.ui.navigation.DemoTransitions
import com.mirego.kmp.boilerplate.app.ui.tabs.Tab1View
import com.mirego.kmp.boilerplate.app.ui.tabs.Tab2View
import com.mirego.kmp.boilerplate.trikot.viewmodels.declarative.compose.vmdViewModel
import com.mirego.kmp.boilerplate.viewmodel.application.ApplicationViewModel

@Composable
fun ApplicationView(applicationViewModel: ApplicationViewModel) {
    val viewModel = vmdViewModel { applicationViewModel }
    val applicationNavController = rememberNavController()

    DemoNavHost(
        navController = applicationNavController,
        navigationManager = viewModel.navigationManager,
        enterTransition = DemoTransitions.Modal.enter,
        exitTransition = DemoTransitions.Modal.exit,
        popEnterTransition = DemoTransitions.Modal.popEnter,
        popExitTransition = DemoTransitions.Modal.popExit
    ) {
        MainView(viewModel)
    }
}

@Composable
private fun MainView(applicationViewModel: ApplicationViewModel) {
    var selectedTab by remember { mutableStateOf(DemoTabs.TAB1) }
    val tab1NavController = rememberNavController()
    val tab2NavController = rememberNavController()

    Scaffold(
        bottomBar = {
            Column {
                BottomNavigation {
                    BottomNavigationItem(
                        icon = { },
                        label = { Text(text = "Tab 1") },
                        selected = selectedTab == DemoTabs.TAB1,
                        onClick = {
                            selectedTab = DemoTabs.TAB1
                        },
                    )
                    BottomNavigationItem(
                        icon = { },
                        label = { Text(text = "Tab 2") },
                        selected = selectedTab == DemoTabs.TAB2,
                        onClick = {
                            selectedTab = DemoTabs.TAB2
                        },
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(navigationBarPadding())
                        .background(MaterialTheme.colors.primarySurface),
                )
            }
        },
    ) { innerPadding ->
        AnimatedContent(
            modifier = Modifier.padding(innerPadding),
            targetState = selectedTab
        ) { tab ->
            when (tab) {
                DemoTabs.TAB1 -> {
                    val viewModel = vmdViewModel {
                        applicationViewModel.tab1ViewModel
                    }
                    DemoNavHost(
                        navController = tab1NavController,
                        navigationManager = viewModel.navigationManager,
                        enterTransition = DemoTransitions.InTab.enter,
                        exitTransition = DemoTransitions.InTab.exit,
                        popEnterTransition = DemoTransitions.InTab.popEnter,
                        popExitTransition = DemoTransitions.InTab.popExit
                    ) {
                        Tab1View(tab1ViewModel = viewModel)
                    }
                }

                DemoTabs.TAB2 -> {
                    val viewModel = vmdViewModel {
                        applicationViewModel.tab2ViewModel
                    }
                    DemoNavHost(
                        navController = tab2NavController,
                        navigationManager = viewModel.navigationManager,
                        enterTransition = DemoTransitions.InTab.enter,
                        exitTransition = DemoTransitions.InTab.exit,
                        popEnterTransition = DemoTransitions.InTab.popEnter,
                        popExitTransition = DemoTransitions.InTab.popExit
                    ) {
                        Tab2View(tab2ViewModel = viewModel)
                    }
                }
            }
        }
    }
}
