package com.mirego.kmp.boilerplate.viewmodels.example

import com.mirego.kmp.boilerplate.Platform
import com.mirego.kmp.boilerplate.usecases.example.ExampleUseCase
import com.mirego.kmp.boilerplate.viewmodels.components.Components
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ExampleViewModelImpl(
    coroutineScope: CoroutineScope,
    exampleUseCase: ExampleUseCase,
    platform: Platform = Platform()
) : ExampleViewModel {

    private val greetingText = buildString {
        appendLine("Hello! 👋")
        appendLine(platform.system)
        appendLine(platform.locale)
        appendLine(platform.version)
    }

    private val initialState = ExampleViewModel.State(
        text = greetingText,
        items = emptyList()
    )

    private val canAddItems = MutableStateFlow(false)

    override val state = exampleUseCase.items
        .map { items ->
            ExampleViewModel.State(
                text = greetingText,
                items = items.map(ExampleViewModel.State::Item)
            )
        }
        .stateIn(coroutineScope, SharingStarted.Lazily, initialState)

    override val button = Components.button("Add an item", enabled = canAddItems) {
        coroutineScope.launch {
            exampleUseCase.addItem("Item ${state.value.items.size + 1}")
        }
    }

    override val toggle = Components.toggle(canAddItems) { isOn ->
        canAddItems.value = isOn
    }

    override val image = Components.image(
        url = "https://raw.githubusercontent.com/onevcat/Kingfisher/master/images/logo.png",
        contentDescription = null
    )
}
