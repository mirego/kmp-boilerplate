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
import com.mirego.kmp.boilerplate.viewmodel.screen.Screen2ViewModel
import com.mirego.trikot.viewmodels.declarative.compose.extensions.observeAsState
import com.mirego.trikot.viewmodels.declarative.compose.viewmodel.VMDButton

@Composable
fun Screen2View(screen2ViewModel: Screen2ViewModel) {
    val viewModel by screen2ViewModel.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(viewModel.title)
        SpacerVertical(height = 64.dp)
        VMDButton(
            modifier = Modifier.height(48.dp),
            viewModel = viewModel.closeButton,
        ) {
            Text(text = it.text)
        }
    }
}
