package com.mirego.kmp.boilerplate.viewmodels.components

import kotlinx.coroutines.flow.StateFlow

interface TextViewModel {
    val state: StateFlow<State>

    data class State(
        val text: String
    )
}
