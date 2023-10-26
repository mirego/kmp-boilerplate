package com.mirego.kmp.boilerplate.app.ui.root

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.mirego.kmp.boilerplate.app.ui.application.ApplicationView
import com.mirego.kmp.boilerplate.app.ui.preview.PreviewProvider
import com.mirego.kmp.boilerplate.viewmodel.root.RootViewModel
import com.mirego.trikot.viewmodels.declarative.compose.extensions.observeAsState

@Composable
fun RootView(rootViewModel: RootViewModel) {
    val viewModel: RootViewModel by rootViewModel.observeAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(text = "Hi")
    }
}

@Preview
@Composable
fun PreviewRootView() {
    PreviewProvider {
        RootView(rootViewModel = it.createRoot())
    }
}
