package com.mirego.kmp.boilerplate.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mirego.kmp.boilerplate.Greeting
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Greeting(text = Greeting().greeting())
        }
    }
}

@Composable
fun Greeting(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun PreviewGreeting() {
    Greeting("Hello, Android 31")
}
