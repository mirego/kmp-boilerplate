package com.mirego.kmp.boilerplate.views.components

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.mirego.kmp.boilerplate.viewmodels.components.ButtonViewModel

@Composable
fun ButtonView(viewModel: ButtonViewModel) {
    val state: ButtonViewModel.State by viewModel.state.collectAsState()

    Button(viewModel::action, enabled = state.enabled) {
        state.text?.let { Text(it) }
    }
}
