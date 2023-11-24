package com.mirego.kmp.boilerplate.app.ui.projectdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.mirego.kmp.boilerplate.app.ui.common.Const.padding
import com.mirego.kmp.boilerplate.app.ui.common.loading
import com.mirego.kmp.boilerplate.app.ui.common.toColor
import com.mirego.kmp.boilerplate.app.ui.theme.TextSize
import com.mirego.kmp.boilerplate.app.ui.theme.TextWeight
import com.mirego.kmp.boilerplate.app.ui.theme.style
import com.mirego.kmp.boilerplate.viewmodel.projectdetails.ProjectDetailsRoot
import com.mirego.trikot.viewmodels.declarative.compose.viewmodel.LocalImage
import com.mirego.trikot.viewmodels.declarative.compose.viewmodel.PlaceholderState
import com.mirego.trikot.viewmodels.declarative.compose.viewmodel.VMDImage
import com.mirego.trikot.viewmodels.declarative.properties.VMDImageResource

@Composable
fun ProjectDetailsContentView(content: ProjectDetailsRoot.Content) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val textColor = content.textColor.toColor()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
    ) {
        VMDImage(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = screenWidth * 1.25f)
                .align(Alignment.TopCenter),
            viewModel = content.image,
            contentScale = ContentScale.FillWidth,
            placeholder = { placeholderImageResource: VMDImageResource, _: PlaceholderState ->
                ImagePlaceholder(
                    placeholderImageResource = placeholderImageResource,
                    color = textColor
                )
            }
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(horizontal = padding)
                .padding(bottom = padding)
        ) {
            Text(
                modifier = Modifier.loading(content.isLoading),
                text = content.title,
                style = style(TextSize.LARGE_TITLE, TextWeight.BOLD),
                color = textColor
            )

            Text(
                modifier = Modifier
                    .padding(top = 24.dp)
                    .loading(content.isLoading),
                text = content.subtitle,
                style = style(TextSize.TITLE1, TextWeight.SEMI_BOLD),
                color = textColor
            )

            Box(
                modifier = Modifier
                    .padding(top = 24.dp)
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(textColor)
            )

            Column(
                modifier = Modifier.padding(top = 32.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    modifier = Modifier.loading(content.isLoading),
                    text = content.projectType.first,
                    style = style(TextSize.SUB_HEADLINE, TextWeight.SEMI_BOLD),
                    color = textColor
                )

                Text(
                    modifier = Modifier.loading(content.isLoading),
                    text = content.projectType.second,
                    style = style(TextSize.SUB_HEADLINE, TextWeight.REGULAR),
                    color = textColor,
                    maxLines = 2
                )
            }

            Column(
                modifier = Modifier.padding(top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    modifier = Modifier.loading(content.isLoading),
                    text = content.releaseYear.first,
                    style = style(TextSize.SUB_HEADLINE, TextWeight.SEMI_BOLD),
                    color = textColor
                )

                Text(
                    modifier = Modifier.loading(content.isLoading),
                    text = content.releaseYear.second,
                    style = style(TextSize.SUB_HEADLINE, TextWeight.REGULAR),
                    color = textColor,
                    maxLines = 2
                )
            }
        }
    }
}

@Composable
private fun ImagePlaceholder(placeholderImageResource: VMDImageResource, color: Color) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        contentAlignment = Alignment.Center
    ) {
        LocalImage(
            modifier = Modifier.size(96.dp),
            imageResource = placeholderImageResource,
            contentScale = ContentScale.FillWidth,
            colorFilter = ColorFilter.tint(color)
        )
    }
}
