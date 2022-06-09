package com.mirego.kmp.boilerplate.android.composables.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mirego.kmp.boilerplate.android.composables.Routable
import com.mirego.kmp.boilerplate.android.composables.Greeting
import com.mirego.kmp.boilerplate.presentation.viewmodel.home.HomeViewModel

@Composable
fun Home(viewModel: HomeViewModel) = Routable(viewModel) {
    Box(modifier = Modifier.fillMaxSize(1.0f), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Greeting(textFlow = viewModel.greetingMessage)

            Spacer(modifier = Modifier.height(8.dp))

            val buttonText: String by viewModel.buttonText.collectAsState("")

            Button(onClick = viewModel::onButtonClick) {
                Text(text = buttonText)
            }
        }
    }
}

@Preview
@Composable
fun PreviewHome() {
    Home(viewModel = HomeViewModel.Preview)
}
