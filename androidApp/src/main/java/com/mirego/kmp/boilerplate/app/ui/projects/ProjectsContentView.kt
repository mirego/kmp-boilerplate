package com.mirego.kmp.boilerplate.app.ui.projects

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mirego.kmp.boilerplate.app.ui.common.Const.padding
import com.mirego.kmp.boilerplate.app.ui.common.EmptyContentView
import com.mirego.kmp.boilerplate.app.ui.common.loading
import com.mirego.kmp.boilerplate.app.ui.preview.PreviewProvider
import com.mirego.kmp.boilerplate.app.ui.theme.AccentOrange
import com.mirego.kmp.boilerplate.app.ui.theme.TextSize
import com.mirego.kmp.boilerplate.app.ui.theme.TextWeight
import com.mirego.kmp.boilerplate.app.ui.theme.style
import com.mirego.kmp.boilerplate.viewmodel.projects.ProjectItem
import com.mirego.kmp.boilerplate.viewmodel.projects.ProjectsContentSection
import com.mirego.pilot.components.ui.pilotImageResourcePainter

@Composable
fun ProjectsContentView(projectSections: List<ProjectsContentSection>) {
    val statusBarPadding = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = padding + statusBarPadding, horizontal = padding),
        verticalArrangement = Arrangement.spacedBy(padding * 2)
    ) {
        items(items = projectSections) { section ->
            when (section) {
                is ProjectsContentSection.Header -> HeaderView(header = section)
                is ProjectsContentSection.NoProjects -> EmptyContentView(
                    emptyViewModel = section.emptyViewModel,
                    modifier = Modifier.padding(top = 100.dp)
                )

                is ProjectsContentSection.ProjectsList -> ProjectsListView(projects = section.projects)
            }
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
private fun ProjectsListView(projects: List<ProjectItem>) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(padding * 2)
    ) {
        projects.forEach { item ->
            ItemView(item)
        }
    }
}

@Composable
private fun ItemView(item: ProjectItem) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .clickable(
                onClick = { item.tapAction.invoke() },
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple()
            ),
        verticalArrangement = Arrangement.spacedBy(padding)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(16.dp))
                .loading(item.isLoading),
            model = item.image.url,
            contentDescription = item.image.contentDescription,
            contentScale = ContentScale.FillWidth,
            placeholder = item.image.placeholder?.let {
                pilotImageResourcePainter(it)
            }
        )

        Column(
            modifier = Modifier
                .padding(horizontal = padding)
        ) {
            Text(
                modifier = Modifier.loading(item.isLoading),
                text = item.title,
                style = style(TextSize.SUB_HEADLINE, TextWeight.REGULAR),
                color = Color.White,
                maxLines = 1
            )

            Text(
                modifier = Modifier
                    .loading(item.isLoading),
                text = item.subtitle,
                style = style(TextSize.TITLE1, TextWeight.REGULAR),
                color = Color.White,
                maxLines = 2
            )

            Text(
                modifier = Modifier
                    .loading(item.isLoading)
                    .padding(top = 12.dp),
                text = item.description,
                style = style(TextSize.CAPTION1, TextWeight.REGULAR),
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
