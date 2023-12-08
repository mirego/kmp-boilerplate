package com.mirego.kmp.boilerplate.app.ui.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.mirego.compose.utils.SpacerVertical
import com.mirego.kmp.boilerplate.viewmodel.dialog.DialogViewModel
import com.mirego.kmp.boilerplate.viewmodel.screen.Screen1ViewModel
import com.mirego.kmp.boilerplate.viewmodel.screen.Screen2ViewModel
import com.mirego.trikot.viewmodels.declarative.compose.extensions.observeAsState
import com.mirego.trikot.viewmodels.declarative.compose.viewmodel.VMDButton
import com.mirego.trikot.viewmodels.declarative.compose.viewmodel.VMDText

@Composable
fun DialogView(dialogViewModel: DialogViewModel) {
    val viewModel by dialogViewModel.observeAsState()

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colors.surface)
            .padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = viewModel.title)
        Text(text = viewModel.message)
        VMDButton(
            modifier = Modifier.height(48.dp),
            viewModel = viewModel.closeButton,
        ) {
            Text(text = it.text)
        }
        viewModel.button.forEach { button ->
            VMDButton(
                modifier = Modifier.height(48.dp),
                viewModel = button,
            ) {
                Text(text = it.text)
            }
        }
    }
}
