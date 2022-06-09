package com.mirego.kmp.boilerplate.android.composables.example

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
import com.mirego.kmp.boilerplate.presentation.viewmodel.example.ExampleViewModel

@Composable
fun Example(viewModel: ExampleViewModel) = Routable(viewModel) {
    val exampleText: String by viewModel.exampleMessage.collectAsState(initial = "")
    val backButtonText: String by viewModel.backButtonText.collectAsState(initial = "")

    Box(modifier = Modifier.fillMaxSize(1.0f), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = exampleText)

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = viewModel::onBackButtonClick) {
                Text(text = backButtonText)
            }
        }
    }
}

@Preview
@Composable
fun PreviewExample() {
    Example(viewModel = ExampleViewModel.Preview)
}
