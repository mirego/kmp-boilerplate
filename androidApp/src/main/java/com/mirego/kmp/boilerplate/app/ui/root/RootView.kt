package com.mirego.kmp.boilerplate.app.ui.root

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mirego.kmp.boilerplate.app.ui.preview.PreviewProvider
import com.mirego.kmp.boilerplate.app.ui.projects.ProjectsView
import com.mirego.kmp.boilerplate.app.ui.theme.PrimaryBlack
import com.mirego.kmp.boilerplate.viewmodel.root.RootViewModel
import com.mirego.trikot.viewmodels.declarative.compose.extensions.observeAsState

@Composable
fun RootView(rootViewModel: RootViewModel) {
    val viewModel: RootViewModel by rootViewModel.observeAsState()
    val systemUiController = rememberSystemUiController()
    systemUiController.setNavigationBarColor(color = Color.PrimaryBlack, darkIcons = false)
    systemUiController.setStatusBarColor(color = Color.Transparent, darkIcons = false)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        ProjectsView(projectsViewModel = viewModel.projectsViewModel)
    }
}

@Preview
@Composable
fun PreviewRootView() {
    PreviewProvider {
        RootView(rootViewModel = it.createRoot())
    }
}
