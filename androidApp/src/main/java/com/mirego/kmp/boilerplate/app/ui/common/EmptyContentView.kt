package com.mirego.kmp.boilerplate.app.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.mirego.kmp.boilerplate.viewmodel.common.EmptyViewModel
import com.mirego.trikot.viewmodels.declarative.compose.extensions.observeAsState
import com.mirego.trikot.viewmodels.declarative.compose.viewmodel.VMDImage
import com.mirego.trikot.viewmodels.declarative.compose.viewmodel.VMDText

@Composable
fun EmptyContentView(emptyViewModel: EmptyViewModel, modifier: Modifier = Modifier) {
    val viewModel: EmptyViewModel by emptyViewModel.observeAsState()
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        VMDImage(
            modifier = Modifier.size(55.dp),
            viewModel = viewModel.icon,
            colorFilter = ColorFilter.tint(Color.White)
        )

        VMDText(
            modifier = Modifier.padding(top = padding * 2),
            viewModel = viewModel.title,
            color = Color.White,
            style = style(TextSize.LARGE_TITLE, TextWeight.REGULAR),
            maxLines = 1
        )

        VMDText(
            modifier = Modifier.padding(top = padding),
            viewModel = viewModel.message,
            color = Color.White,
            style = style(TextSize.BODY, TextWeight.REGULAR),
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun PreviewEmptyContentView() {
    PreviewProvider {
        ProjectsView(projectsViewModel = it.createProjects(previewState = PreviewState.Data.Empty))
    }
}
