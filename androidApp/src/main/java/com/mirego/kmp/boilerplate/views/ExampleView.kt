package com.mirego.kmp.boilerplate.views

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mirego.kmp.boilerplate.previews.PreviewContext
import com.mirego.kmp.boilerplate.viewmodels.example.ExampleViewModel

@Composable
fun ExampleView(viewModel: ExampleViewModel) {
    val state: ExampleViewModel.State by viewModel.state.collectAsStateWithLifecycle()

    Text(text = state.greeting)
}

@Preview(showSystemUi = true)
@Composable
fun PreviewExampleView() {
    PreviewContext { viewModelFactory ->
        ExampleView(
            viewModel = viewModelFactory.example()
        )
    }
}
