package com.mirego.kmp.boilerplate

import com.mirego.kmp.boilerplate.platform.Platform
import com.mirego.kmp.boilerplate.utils.CFlow
import com.mirego.kmp.boilerplate.utils.wrap
import kotlinx.coroutines.flow.flowOf

class Greeting {
    private val platform = Platform()

    private val greetingText = buildString {
        appendLine("Hello! 👋")
        appendLine(platform.system)
        appendLine(platform.locale)
        appendLine(platform.version)
    }

    fun greeting(): CFlow<String> = flowOf(greetingText).wrap()
}
