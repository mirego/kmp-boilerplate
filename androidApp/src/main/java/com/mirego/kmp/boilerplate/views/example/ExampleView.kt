package com.mirego.kmp.boilerplate.views.example

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.mirego.kmp.boilerplate.previews.PreviewProvider
import com.mirego.kmp.boilerplate.viewmodels.example.ExampleViewModel
import com.mirego.kmp.boilerplate.views.components.ButtonView
import com.mirego.kmp.boilerplate.views.components.ImageView
import com.mirego.kmp.boilerplate.views.components.ToggleView

@Composable
fun ExampleView(viewModel: ExampleViewModel) {
    val state: ExampleViewModel.State by viewModel.state.collectAsState()

    Column {
        Text(state.text)

        LazyColumn {
            items(state.items, key = ExampleViewModel.State.Item::identifier) { item ->
                Text(text = item.text)
            }
        }

        ButtonView(viewModel = viewModel.button)

        ToggleView(viewModel = viewModel.toggle)

        ImageView(viewModel = viewModel.image)
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewGreeting() {
    PreviewProvider { coroutineScope, viewModelFactory ->
        ExampleView(viewModel = viewModelFactory.exampleViewModel(coroutineScope))
    }
}
