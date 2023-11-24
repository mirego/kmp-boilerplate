package com.mirego.kmp.boilerplate.app.ui.common

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import com.mirego.kmp.boilerplate.app.ui.theme.ShimmerBackground
import com.mirego.kmp.boilerplate.app.ui.theme.ShimmerHighlight
import com.mirego.trikot.viewmodels.declarative.properties.VMDColor

fun Modifier.loading(isLoading: Boolean) = this.then(
    placeholder(
        visible = isLoading,
        highlight = PlaceholderHighlight.shimmer(highlightColor = Color.ShimmerHighlight),
        color = Color.ShimmerBackground
    )
)

fun VMDColor.toColor() = Color(red, green, blue)
