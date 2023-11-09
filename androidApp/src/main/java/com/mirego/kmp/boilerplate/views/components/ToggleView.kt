package com.mirego.kmp.boilerplate.views.components

import androidx.compose.material.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.mirego.kmp.boilerplate.viewmodels.components.ToggleViewModel

@Composable
fun ToggleView(viewModel: ToggleViewModel) {
    val state: ToggleViewModel.State by viewModel.state.collectAsState()

    Switch(
        checked = state.isOn,
        enabled = state.enabled,
        onCheckedChange = viewModel::toggle
    )
}
