package com.mirego.kmp.boilerplate.app.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mirego.kmp.boilerplate.app.ui.common.Const.padding
import com.mirego.kmp.boilerplate.app.ui.preview.PreviewProvider
import com.mirego.kmp.boilerplate.app.ui.projects.ProjectsView
import com.mirego.kmp.boilerplate.app.ui.theme.TextSize
import com.mirego.kmp.boilerplate.app.ui.theme.TextWeight
import com.mirego.kmp.boilerplate.app.ui.theme.style
import com.mirego.kmp.boilerplate.usecase.preview.PreviewState
import com.mirego.kmp.boilerplate.viewmodel.common.ErrorViewModel
import com.mirego.pilot.components.ui.PilotButton
import com.mirego.pilot.components.ui.pilotImageResourcePainter
import com.mirego.trikot.viewmodels.declarative.compose.extensions.observeAsState

@Composable
fun ErrorView(errorViewModel: ErrorViewModel) {
    val iconPadding = 4.dp
    val viewModel: ErrorViewModel by errorViewModel.observeAsState()
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = pilotImageResourcePainter(viewModel.icon.imageResource),
            contentDescription = viewModel.icon.contentDescription,
            modifier = Modifier.size(55.dp),
            colorFilter = ColorFilter.tint(Color.White)
        )

        Text(
            modifier = Modifier.padding(top = padding * 2 - iconPadding),
            text = viewModel.title,
            style = style(TextSize.LARGE_TITLE, TextWeight.REGULAR),
            color = Color.White,
            maxLines = 1
        )

        Text(
            modifier = Modifier.padding(top = padding),
            text = viewModel.message,
            style = style(TextSize.BODY, TextWeight.REGULAR),
            color = Color.White,
            textAlign = TextAlign.Center
        )

        PilotButton(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = 320.dp)
                .padding(top = padding * 2)
                .padding(horizontal = padding * 2)
                .clip(RoundedCornerShape(percent = 50))
                .background(Color.Red)
                .padding(all = 12.dp),
            pilotButton = viewModel.retryButton
        ) { content ->
            Text(
                modifier = Modifier,
                text = content,
                style = style(TextSize.BODY, TextWeight.REGULAR),
                color = Color.White
            )
        }
    }
}

@Preview
@Composable
fun PreviewProjectsView() {
    PreviewProvider {
        ProjectsView(projectsViewModel = it.createProjects(previewState = PreviewState.Error))
    }
}
