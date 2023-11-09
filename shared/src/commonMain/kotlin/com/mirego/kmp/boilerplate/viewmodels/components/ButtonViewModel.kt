package com.mirego.kmp.boilerplate.viewmodels.components

import kotlinx.coroutines.flow.StateFlow

interface ButtonViewModel {
    val state: StateFlow<State>

    fun action()

    data class State(
        val text: String? = null,
        val enabled: Boolean = true
    )
}
