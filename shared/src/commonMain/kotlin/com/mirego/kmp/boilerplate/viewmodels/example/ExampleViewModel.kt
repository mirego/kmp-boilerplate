package com.mirego.kmp.boilerplate.viewmodels.example

import com.mirego.kmp.boilerplate.viewmodels.components.ButtonViewModel
import com.mirego.kmp.boilerplate.viewmodels.components.ImageViewModel
import com.mirego.kmp.boilerplate.viewmodels.components.ListIdentifiable
import com.mirego.kmp.boilerplate.viewmodels.components.ToggleViewModel
import kotlinx.coroutines.flow.StateFlow

interface ExampleViewModel {
    val state: StateFlow<State>

    val button: ButtonViewModel
    val toggle: ToggleViewModel
    val image: ImageViewModel

    data class State(
        val text: String,
        val items: List<Item>
    ) {
        data class Item(val text: String) : ListIdentifiable {
            override val identifier = text
        }
    }
}
