package com.mirego.kmp.boilerplate.app.ui.projects

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mirego.kmp.boilerplate.app.ui.common.Const.padding
import com.mirego.kmp.boilerplate.app.ui.common.EmptyContentView
import com.mirego.kmp.boilerplate.app.ui.preview.PreviewProvider
import com.mirego.kmp.boilerplate.app.ui.theme.AccentOrange
import com.mirego.kmp.boilerplate.app.ui.theme.TextSize
import com.mirego.kmp.boilerplate.app.ui.theme.TextWeight
import com.mirego.kmp.boilerplate.app.ui.theme.style
import com.mirego.kmp.boilerplate.viewmodel.projects.ProjectItem
import com.mirego.kmp.boilerplate.viewmodel.projects.ProjectsContentSection
import com.mirego.trikot.viewmodels.declarative.components.VMDListViewModel
import com.mirego.trikot.viewmodels.declarative.compose.extensions.observeAsState
import com.mirego.trikot.viewmodels.declarative.compose.viewmodel.VMDImage
import com.mirego.trikot.viewmodels.declarative.compose.viewmodel.VMDLazyColumn

@Composable
fun ProjectsContentView(listViewModel: VMDListViewModel<ProjectsContentSection>) {
    val viewModel: VMDListViewModel<ProjectsContentSection> by listViewModel.observeAsState()
    val statusBarPadding = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    VMDLazyColumn(
        modifier = Modifier.fillMaxSize(),
        viewModel = viewModel,
        contentPadding = PaddingValues(vertical = padding + statusBarPadding, horizontal = padding),
        verticalArrangement = Arrangement.spacedBy(padding * 2)
    ) { section ->
        when (section) {
            is ProjectsContentSection.Header -> HeaderView(header = section)
            is ProjectsContentSection.NoProjects -> EmptyContentView(emptyViewModel = section.emptyViewModel)
            is ProjectsContentSection.ProjectsList -> ProjectsListView(viewModel = section.viewModel)
        }
    }
}

@Composable
private fun HeaderView(header: ProjectsContentSection.Header) {
    Column(
        modifier = Modifier.padding(horizontal = padding),
        verticalArrangement = Arrangement.spacedBy(padding * 2),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = header.title,
            style = style(TextSize.TITLE1, TextWeight.SEMI_BOLD),
            color = Color.White,
            maxLines = 1
        )

        Text(
            text = header.description,
            style = style(TextSize.HEADLINE, TextWeight.REGULAR),
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun ProjectsListView(viewModel: VMDListViewModel<ProjectItem>) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(padding)
    ) {
        viewModel.elements.forEach { item ->
            ItemView(item)
        }
    }
}

@Composable
private fun ItemView(item: ProjectItem) {
    Column(
        verticalArrangement = Arrangement.spacedBy(padding)
    ) {
        VMDImage(
            modifier = Modifier.clip(RoundedCornerShape(16.dp)),
            viewModel = item.image,
            contentScale = ContentScale.FillWidth
        )

        Column(
            modifier = Modifier
                .padding(horizontal = padding)
                .padding(top = padding)
        ) {
            Text(
                text = item.title,
                style = style(TextSize.SUB_HEADLINE, TextWeight.REGULAR),
                color = Color.White,
                maxLines = 1
            )

            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = item.subtitle,
                style = style(TextSize.SUB_HEADLINE, TextWeight.REGULAR),
                color = Color.White,
                maxLines = 2
            )

            Text(
                modifier = Modifier.padding(top = padding),
                text = item.description,
                style = style(TextSize.SUB_HEADLINE, TextWeight.REGULAR),
                color = Color.AccentOrange,
                maxLines = 2
            )
        }
    }
}

@Preview
@Composable
fun PreviewProjectsContentView() {
    PreviewProvider {
        ProjectsView(projectsViewModel = it.createProjects())
    }
}
