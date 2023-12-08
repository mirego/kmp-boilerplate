package com.mirego.kmp.boilerplate.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mirego.compose.utils.SpacerVertical
import com.mirego.kmp.boilerplate.viewmodel.screen.Screen2ViewModel
import com.mirego.kmp.boilerplate.viewmodel.screen.Screen3ViewModel
import com.mirego.trikot.viewmodels.declarative.compose.extensions.observeAsState
import com.mirego.trikot.viewmodels.declarative.compose.viewmodel.VMDButton
import com.mirego.trikot.viewmodels.declarative.compose.viewmodel.VMDLifecycleView

@Composable
fun Screen3View(screen3ViewModel: Screen3ViewModel) {
    val viewModel by screen3ViewModel.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SpacerVertical(height = 50.dp)
        Text(text = viewModel.title)
        SpacerVertical(height = 50.dp)
        VMDButton(
            modifier = Modifier.height(48.dp),
            viewModel = viewModel.closeButton,
        ) {
            Text(text = it.text)
        }
        VMDButton(
            modifier = Modifier.height(48.dp),
            viewModel = viewModel.pushButton,
        ) {
            Text(text = it.text)
        }
        VMDButton(
            modifier = Modifier.height(48.dp),
            viewModel = viewModel.dialogButton,
        ) {
            Text(text = it.text)
        }
    }
}
