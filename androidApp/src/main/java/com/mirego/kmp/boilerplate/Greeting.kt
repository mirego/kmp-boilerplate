package com.mirego.kmp.boilerplate

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.mirego.kmp.boilerplate.previews.PreviewContext
import kotlinx.coroutines.flow.Flow

@Composable
fun Greeting(textFlow: Flow<String>) {
    val text: String by textFlow.collectAsState("initial")

    Text(text = text)
}

@Preview(showSystemUi = true)
@Composable
fun PreviewGreeting() {
    PreviewContext {
        Greeting(Greeting().greeting())
    }
}
