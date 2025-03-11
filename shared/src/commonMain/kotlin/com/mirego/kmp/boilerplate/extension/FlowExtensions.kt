package com.mirego.kmp.boilerplate.extension

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

fun <T> stateFlowOf(value: T): StateFlow<T> = MutableStateFlow(value)
