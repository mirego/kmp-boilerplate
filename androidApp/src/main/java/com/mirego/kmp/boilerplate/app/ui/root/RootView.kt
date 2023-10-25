package com.mirego.kmp.boilerplate.app.ui.root

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.mirego.kmp.boilerplate.viewmodel.root.RootViewModel
import com.mirego.trikot.viewmodels.declarative.compose.extensions.observeAsState

@Composable
fun RootView(rootViewModel: RootViewModel) {
    val viewModel: RootViewModel by rootViewModel.observeAsState()
    Text(text = "Hi")
}
