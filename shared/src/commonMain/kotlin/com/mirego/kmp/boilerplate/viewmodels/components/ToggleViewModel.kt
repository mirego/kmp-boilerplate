package com.mirego.kmp.boilerplate.viewmodels.components

import kotlinx.coroutines.flow.StateFlow

interface ToggleViewModel {
    val state: StateFlow<State>

    fun toggle(isOn: Boolean)

    data class State(
        val isOn: Boolean,
        val text: String? = null,
        val enabled: Boolean = true
    )
}
