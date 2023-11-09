package com.mirego.kmp.boilerplate.viewmodels.components

import com.mirego.kmp.boilerplate.viewmodels.extensions.mapState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object Components {
    fun button(text: String, enabled: StateFlow<Boolean>, action: () -> Unit) =
        object : ButtonViewModel {
            override val state = enabled.mapState { enabled ->
                ButtonViewModel.State(
                    text = text,
                    enabled = enabled
                )
            }

            override fun action() = action()
        }

    fun image(url: String, contentDescription: String?): ImageViewModel =
        object : ImageViewModel {
            override val state = MutableStateFlow(
                ImageViewModel.State(
                    url = url,
                    contentDescription = contentDescription
                )
            )
        }

    fun toggle(isOn: StateFlow<Boolean>, toggle: (Boolean) -> Unit): ToggleViewModel =
        object : ToggleViewModel {
            override val state = isOn.mapState { isOn ->
                ToggleViewModel.State(
                    isOn = isOn
                )
            }

            override fun toggle(isOn: Boolean) = toggle(isOn)
        }
}
