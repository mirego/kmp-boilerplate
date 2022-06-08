package com.mirego.kmp.boilerplate.android.composables

import androidx.compose.runtime.Composable
import com.mirego.kmp.boilerplate.Greeting

@Composable
fun Home() {
    Greeting(textFlow = Greeting().greeting())
}
