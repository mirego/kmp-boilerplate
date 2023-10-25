package com.mirego.kmp.boilerplate.app.ui.application

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.mirego.kmp.boilerplate.app.ui.root.RootView
import com.mirego.kmp.boilerplate.viewmodel.application.ApplicationViewModel
import com.mirego.trikot.viewmodels.declarative.compose.extensions.observeAsState

@Composable
fun ApplicationView(applicationViewModel: ApplicationViewModel) {
    val viewModel: ApplicationViewModel by applicationViewModel.observeAsState()
    RootView(rootViewModel = viewModel.rootViewModel)
}
