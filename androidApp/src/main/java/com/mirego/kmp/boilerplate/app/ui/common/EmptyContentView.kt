package com.mirego.kmp.boilerplate.app.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
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
//import com.mirego.kmp.boilerplate.app.ui.preview.PreviewProvider
import com.mirego.kmp.boilerplate.app.ui.projects.ProjectsView
import com.mirego.kmp.boilerplate.app.ui.theme.TextSize
import com.mirego.kmp.boilerplate.app.ui.theme.TextWeight
import com.mirego.kmp.boilerplate.app.ui.theme.style
import com.mirego.kmp.boilerplate.usecase.preview.PreviewState
import com.mirego.kmp.boilerplate.viewmodel.common.EmptyViewModel
import com.mirego.pilot.components.ui.pilotImageResourcePainter
import com.mirego.trikot.viewmodels.declarative.compose.extensions.observeAsState

@Composable
fun EmptyContentView(emptyViewModel: EmptyViewModel, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(55.dp),
            painter = pilotImageResourcePainter(emptyViewModel.icon.imageResource),
            colorFilter = ColorFilter.tint(Color.White),
            contentDescription = emptyViewModel.icon.contentDescription
        )

        Text(
            modifier = Modifier.padding(top = padding * 2),
            text = emptyViewModel.title,
            color = Color.White,
            style = style(TextSize.LARGE_TITLE, TextWeight.REGULAR),
            maxLines = 1
        )

        Text(
            modifier = Modifier.padding(top = padding),
            text = emptyViewModel.message,
            color = Color.White,
            style = style(TextSize.BODY, TextWeight.REGULAR),
            textAlign = TextAlign.Center
        )
    }
}

//@Preview
//@Composable
//fun PreviewEmptyContentView() {
//    PreviewProvider {
//        ProjectsView(projectsViewModel = it.createProjects(previewState = PreviewState.Data.Empty))
//    }
//}
