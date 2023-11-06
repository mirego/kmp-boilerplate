package com.mirego.kmp.boilerplate.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer

@Composable
fun Theme(
    content: @Composable () -> Unit
) {
    val colors = lightColorScheme(
        primary = Color.AccentOrange
    )

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}
