package com.mirego.kmp.boilerplate

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class Greeting {
    fun greeting(): Flow<String> = flowOf("Hello, ${Platform().platform}!")
}
