package com.mirego.kmp.boilerplate.app.ui.tabs

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
import androidx.compose.ui.unit.dp
import com.mirego.compose.utils.SpacerVertical
import com.mirego.kmp.boilerplate.viewmodel.tab.Tab1ViewModel
import com.mirego.trikot.viewmodels.declarative.compose.extensions.observeAsState
import com.mirego.trikot.viewmodels.declarative.compose.viewmodel.VMDButton
import com.mirego.trikot.viewmodels.declarative.compose.viewmodel.VMDLifecycleView
import com.mirego.trikot.viewmodels.declarative.compose.viewmodel.VMDText

@Composable
fun Tab1View(tab1ViewModel: Tab1ViewModel) {
    val viewModel by tab1ViewModel.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = viewModel.title)
        SpacerVertical(height = 50.dp)
        VMDButton(
            modifier = Modifier.height(48.dp),
            viewModel = viewModel.openExternalUrl,
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
            viewModel = viewModel.dialogButton,
        ) {
            Text(text = it.text)
        }
        VMDText(viewModel = viewModel.dialogResult)
    }
}
