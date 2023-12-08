package com.mirego.kmp.boilerplate.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mirego.compose.utils.SpacerVertical
import com.mirego.kmp.boilerplate.viewmodel.screen.Screen1ViewModel
import com.mirego.trikot.viewmodels.declarative.compose.extensions.observeAsState
import com.mirego.trikot.viewmodels.declarative.compose.viewmodel.VMDButton
import com.mirego.trikot.viewmodels.declarative.compose.viewmodel.VMDText

@Composable
fun Screen1View(screen1ViewModel: Screen1ViewModel) {
    val viewModel by screen1ViewModel.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        VMDText(viewModel = viewModel.title)
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
            viewModel = viewModel.modalButton,
        ) {
            Text(text = it.text)
        }
        VMDButton(
            modifier = Modifier.height(48.dp),
            viewModel = viewModel.popToRoot,
        ) {
            Text(text = it.text)
        }
        VMDButton(
            modifier = Modifier.height(48.dp),
            viewModel = viewModel.popToScreen3Inclusive,
        ) {
            Text(text = it.text)
        }
        VMDButton(
            modifier = Modifier.height(48.dp),
            viewModel = viewModel.popToScreen3Exclusive,
        ) {
            Text(text = it.text)
        }
    }
}
