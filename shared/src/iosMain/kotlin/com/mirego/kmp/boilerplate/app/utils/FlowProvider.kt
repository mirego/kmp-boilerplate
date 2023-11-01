package com.mirego.kmp.boilerplate.app.utils

import com.mirego.kmp.boilerplate.utils.ConcreteFlow
import com.mirego.kmp.boilerplate.utils.ConcreteMutableSharedFlow
import com.mirego.kmp.boilerplate.utils.ConcreteMutableStateFlow
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

class FlowProvider<T> {

    fun flowOf(value: T): ConcreteFlow<T> = ConcreteFlow(kotlinx.coroutines.flow.flowOf(value))

    fun flowOf(vararg elements: T): Flow<T> = ConcreteFlow(kotlinx.coroutines.flow.flowOf(*elements))

    fun mutableSharedFlow(): ConcreteMutableSharedFlow<T> = ConcreteMutableSharedFlow(MutableSharedFlow())

    fun mutableSharedFlow(replay: Int = 0, extraBufferCapacity: Int = 0, onBufferOverflow: BufferOverflow = BufferOverflow.SUSPEND): ConcreteMutableSharedFlow<T> =
        ConcreteMutableSharedFlow(MutableSharedFlow(replay, extraBufferCapacity, onBufferOverflow))

    fun mutableStateFlow(initialValue: T): ConcreteMutableStateFlow<T> = ConcreteMutableStateFlow(MutableStateFlow(initialValue))
}
