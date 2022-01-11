package com.mirego.kmp.boilerplate.presentation.routing

sealed class Screen {
    object Home : Screen()
    data class Example(val origin: String) : Screen()
}
