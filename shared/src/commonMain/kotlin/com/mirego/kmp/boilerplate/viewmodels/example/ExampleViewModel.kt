package com.mirego.kmp.boilerplate.viewmodels.example

import com.mirego.kmp.boilerplate.platform.Platform
import com.mirego.kmp.boilerplate.viewmodels.BaseViewModel
import com.mirego.kmp.boilerplate.viewmodels.ViewModel
import com.mirego.konnectivity.Konnectivity
import com.mirego.konnectivity.NetworkState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlin.time.Duration.Companion.seconds

interface ExampleViewModel : ViewModel {
    val state: StateFlow<State>

    data class State(
        val greeting: String,
    )
}

class ExampleViewModelImpl(
    coroutineScope: CoroutineScope,
    platform: Platform,
    konnectivity: Konnectivity,
) : BaseViewModel(coroutineScope), ExampleViewModel {
    private val greetingText =
        buildString {
            appendLine("Hello! 👋")
            appendLine(platform.system)
            appendLine(platform.locale)
            appendLine(platform.version)
            appendLine()
        }

    override val state: StateFlow<ExampleViewModel.State> =
        konnectivity.networkState
            .map { networkState ->
                ExampleViewModel.State(greeting = greetingText + networkState.asGreetingInfo())
            }
            .stateIn(
                coroutineScope,
                SharingStarted.WhileSubscribed(5.seconds.inWholeMilliseconds),
                ExampleViewModel.State(greeting = greetingText),
            )

    private fun NetworkState.asGreetingInfo(): String =
        "By the way, you're " +
            when (this) {
                NetworkState.Unreachable -> "offline. 🔌"
                is NetworkState.Reachable ->
                    when (metered) {
                        true -> "online, but your connection is metered. 📶"
                        else -> "online! 🛜"
                    }
            }
}
