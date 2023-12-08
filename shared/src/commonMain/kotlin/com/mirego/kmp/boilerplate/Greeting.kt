package com.mirego.kmp.boilerplate

import com.mirego.kmp.boilerplate.platform.Platform
import com.mirego.kmp.boilerplate.utils.CFlow
import com.mirego.kmp.boilerplate.utils.wrap
import com.mirego.konnectivity.Konnectivity
import com.mirego.konnectivity.NetworkState
import kotlinx.coroutines.flow.map

class Greeting {
    private val platform = Platform()
    private val konnectivity = Konnectivity()

    private val greetingText = buildString {
        appendLine("Hello! 👋")
        appendLine(platform.system)
        appendLine(platform.locale)
        appendLine(platform.version)
        appendLine()
    }

    fun greeting(): CFlow<String> = konnectivity.networkState
        .map { networkState ->
            greetingText + networkState.asGreetingInfo()
        }
        .wrap()

    private fun NetworkState.asGreetingInfo(): String = "By the way, you're " + when (this) {
        NetworkState.Unreachable -> "offline. 🔌"
        is NetworkState.Reachable -> when (metered) {
            true -> "online, but your connection is metered. 📶"
            else -> "online! 🛜"
        }
    }
}
