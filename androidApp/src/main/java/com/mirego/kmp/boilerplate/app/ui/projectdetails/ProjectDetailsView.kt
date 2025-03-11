package com.mirego.kmp.boilerplate.app.ui.projectdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.SystemUiController
import com.mirego.kmp.boilerplate.app.ui.common.ErrorView
import com.mirego.kmp.boilerplate.app.ui.common.toColor
import com.mirego.kmp.boilerplate.app.ui.preview.PreviewProvider
import com.mirego.kmp.boilerplate.usecase.preview.PreviewState
import com.mirego.kmp.boilerplate.viewmodel.projectdetails.ProjectDetailsRoot
import com.mirego.kmp.boilerplate.viewmodel.projectdetails.ProjectDetailsViewModel
import com.mirego.pilot.components.ui.PilotButton
import com.mirego.pilot.components.ui.pilotImageResourcePainter
import com.mirego.trikot.viewmodels.declarative.compose.extensions.observeAsState

@Composable
fun ProjectDetailsView(projectDetailsViewModel: ProjectDetailsViewModel, systemUiController: SystemUiController? = null) {
    val viewModel: ProjectDetailsViewModel by projectDetailsViewModel.observeAsState()
    systemUiController?.setNavigationBarColor(color = viewModel.backgroundColor.toColor(), darkIcons = false)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(viewModel.backgroundColor.toColor())
    ) {
        ContentView(viewModel = viewModel)

        PilotButton(
            modifier = Modifier
                .statusBarsPadding()
                .padding(8.dp)
                .clip(CircleShape)
                .size(40.dp)
                .background(viewModel.textColor.toColor().copy(alpha = 0.1f)),
            pilotButton = viewModel.closeButton
        ) { content ->
            Image(
                modifier = Modifier
                    .size(32.dp)
                    .align(Alignment.Center),
                painter = pilotImageResourcePainter(content.imageResource),
                colorFilter = ColorFilter.tint(viewModel.textColor.toColor()),
                contentDescription = content.contentDescription
            )
        }
    }
}

@Composable
private fun ContentView(viewModel: ProjectDetailsViewModel) {
    val projectDetailsViewModel: ProjectDetailsViewModel by viewModel.observeAsState()
    projectDetailsViewModel.rootContent?.let { content ->
        when (content) {
            is ProjectDetailsRoot.Content -> ProjectDetailsContentView(content = content)
            is ProjectDetailsRoot.Error -> ErrorView(errorViewModel = content.errorViewModel)
        }
    }
}

@Preview
@Composable
fun PreviewProjectsDetailsContentView() {
    PreviewProvider {
        ProjectDetailsView(projectDetailsViewModel = it.createProjectDetails(previewState = PreviewState.Data.Content))
    }
}

@Preview
@Composable
fun PreviewProjectsDetailsEmptyView() {
    PreviewProvider {
        ProjectDetailsView(projectDetailsViewModel = it.createProjectDetails(previewState = PreviewState.Data.Empty))
    }
}

@Preview
@Composable
fun PreviewProjectsDetailsLoadingView() {
    PreviewProvider {
        ProjectDetailsView(projectDetailsViewModel = it.createProjectDetails(previewState = PreviewState.Loading))
    }
}

@Preview
@Composable
fun PreviewProjectsDetailsErrorView() {
    PreviewProvider {
        ProjectDetailsView(projectDetailsViewModel = it.createProjectDetails(previewState = PreviewState.Error))
    }
}
