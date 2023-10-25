package com.mirego.kmp.boilerplate.viewmodel

import com.mirego.kmp.boilerplate.Platform
import com.mirego.kmp.boilerplate.utils.CFlow
import com.mirego.kmp.boilerplate.utils.wrap
import kotlinx.coroutines.flow.flowOf

class Greeting {
    fun greeting(): CFlow<String> = flowOf("Hello, ${Platform().platform}!").wrap()
}
