package com.mirego.kmp.boilerplate.views.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.mirego.kmp.boilerplate.viewmodels.components.ImageViewModel

@Composable
fun ImageView(viewModel: ImageViewModel) {
    val state: ImageViewModel.State by viewModel.state.collectAsState()

    AsyncImage(
        model = state.url,
        contentDescription = state.contentDescription,
        contentScale = ContentScale.Fit
    )
}
