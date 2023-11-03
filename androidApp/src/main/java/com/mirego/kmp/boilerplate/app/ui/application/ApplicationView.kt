package com.mirego.kmp.boilerplate.app.ui.application

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.mirego.kmp.boilerplate.app.ui.preview.PreviewProvider
import com.mirego.kmp.boilerplate.app.ui.root.RootView
import com.mirego.kmp.boilerplate.app.ui.theme.Theme
import com.mirego.kmp.boilerplate.viewmodel.application.ApplicationViewModel
import com.mirego.trikot.viewmodels.declarative.compose.extensions.observeAsState

@Composable
fun ApplicationView(applicationViewModel: ApplicationViewModel) {
    val viewModel: ApplicationViewModel by applicationViewModel.observeAsState()
    Theme {
        RootView(rootViewModel = viewModel.rootViewModel)
    }
}

@Preview
@Composable
fun PreviewApplicationView() {
    PreviewProvider {
        ApplicationView(applicationViewModel = it.createApplication())
    }
}
