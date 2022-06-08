package com.mirego.kmp.boilerplate.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.mirego.kmp.boilerplate.Greeting
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Greeting(textFlow = Greeting().greeting())
        }
    }
}

@Composable
fun Greeting(textFlow: Flow<String>) {
    val text: String by textFlow.collectAsState("initial")

    Text(text = text)
}

@Preview
@Composable
fun PreviewGreeting() {
    val textFlow = flowOf("Hello, Android 31")
    Greeting(textFlow)
}
