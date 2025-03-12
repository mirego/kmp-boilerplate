package com.mirego.kmp.boilerplate.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.mirego.kmp.boilerplate.app.resources.AndroidImageProvider
import com.mirego.pilot.components.ui.PilotResources

@Composable
fun Theme(
    content: @Composable () -> Unit
) {
    val colors = lightColorScheme(
        primary = Color.AccentOrange
    )

    PilotResources(
        imageResourceProvider = AndroidImageProvider()
    ) {
        MaterialTheme(
            colorScheme = colors,
            content = content
        )
    }
}
