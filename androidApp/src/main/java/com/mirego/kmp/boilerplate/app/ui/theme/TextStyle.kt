package com.mirego.kmp.boilerplate.app.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

fun style(size: TextSize, weight: TextWeight) = TextStyle(
    fontSize = size.fontSize(),
    fontStyle = FontStyle.Normal,
    fontWeight = weight.fontName()
)

enum class TextSize {
    LARGE_TITLE,
    TITLE1,
    TITLE2,
    TITLE3,
    HEADLINE,
    BODY,
    CALLOUT,
    SUB_HEADLINE,
    FOOTNOTE,
    CAPTION1,
    CAPTION2;

    fun fontSize() = when (this) {
        LARGE_TITLE -> 34.sp
        TITLE1 -> 28.sp
        TITLE2 -> 22.sp
        TITLE3 -> 20.sp
        HEADLINE -> 18.sp
        BODY -> 17.sp
        CALLOUT -> 16.sp
        SUB_HEADLINE -> 15.sp
        FOOTNOTE -> 13.sp
        CAPTION1 -> 12.sp
        CAPTION2 -> 11.sp
    }
}

enum class TextWeight {
    LIGHT,
    REGULAR,
    MEDIUM,
    SEMI_BOLD,
    BOLD;

    fun fontName() = when (this) {
        LIGHT -> FontWeight.Light
        REGULAR -> FontWeight.Normal
        MEDIUM -> FontWeight.Medium
        SEMI_BOLD -> FontWeight.SemiBold
        BOLD -> FontWeight.Bold
    }
}
