package com.mirego.kmp.boilerplate

import com.mirego.kmp.boilerplate.utils.CFlow
import com.mirego.kmp.boilerplate.utils.wrap
import kotlinx.coroutines.flow.flowOf

class Greeting {
    fun greeting(): CFlow<String> = flowOf("Hello, ${Platform().platform}!").wrap()
}
