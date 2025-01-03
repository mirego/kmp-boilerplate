package com.mirego.kmp.boilerplate.extension

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn

fun <T> stateFlowOf(value: T): StateFlow<T> = MutableStateFlow(value)

fun <T> Flow<T>.eagerlyStateIn(scope: CoroutineScope, initialValue: T): StateFlow<T> =
    flowOn(Dispatchers.Default).stateIn(scope, SharingStarted.Eagerly, initialValue)
