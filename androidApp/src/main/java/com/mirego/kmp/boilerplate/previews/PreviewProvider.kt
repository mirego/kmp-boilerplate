package com.mirego.kmp.boilerplate.previews

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.mirego.kmp.boilerplate.AppBootstrapper
import com.mirego.kmp.boilerplate.viewmodels.ViewModelFactory
import kotlinx.coroutines.CoroutineScope

@Composable
fun PreviewProvider(content: @Composable (CoroutineScope, ViewModelFactory) -> Unit) {
    val coroutineScope = rememberCoroutineScope()
    val viewModelFactory = AppBootstrapper().viewModelFactory

    content(coroutineScope, viewModelFactory)
}
