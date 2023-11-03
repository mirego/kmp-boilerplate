package com.mirego.kmp.boilerplate.app.ui.projects

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.mirego.kmp.boilerplate.app.ui.common.ErrorView
import com.mirego.kmp.boilerplate.app.ui.preview.PreviewProvider
import com.mirego.kmp.boilerplate.app.ui.theme.PrimaryBlack
import com.mirego.kmp.boilerplate.viewmodel.projects.ProjectsRoot
import com.mirego.kmp.boilerplate.viewmodel.projects.ProjectsViewModel
import com.mirego.trikot.viewmodels.declarative.compose.extensions.observeAsState

@Composable
fun ProjectsView(projectsViewModel: ProjectsViewModel) {
    val viewModel: ProjectsViewModel by projectsViewModel.observeAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .background(Color.PrimaryBlack),
    ) {
        ContentView(viewModel = viewModel)
    }
}

@Composable
private fun ContentView(viewModel: ProjectsViewModel) {
    viewModel.rootContent?.let { content ->
        when(content) {
            is ProjectsRoot.Content -> ProjectsContentView(listViewModel = content.sections)
            is ProjectsRoot.Error -> ErrorView(errorViewModel = content.errorViewModel)
        }
    }
}

@Preview
@Composable
fun PreviewProjectsView() {
    PreviewProvider {
        ProjectsView(projectsViewModel = it.createProjects())
    }
}
