package com.mirego.kmp.boilerplate.utils

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

open class ConcreteFlow<T>(private val internalFlow: Flow<T>) : Flow<T> {
    override suspend fun collect(collector: FlowCollector<T>) {
        internalFlow.collect(collector)
    }
}

fun <T> Flow<T>.wrap(): ConcreteFlow<T> = ConcreteFlow(this)

open class ConcreteMutableSharedFlow<T>(private val mutableSharedFlow: MutableSharedFlow<T>) : MutableSharedFlow<T>, ConcreteFlow<T>(mutableSharedFlow) {
    override val subscriptionCount: StateFlow<Int> = mutableSharedFlow.subscriptionCount

    override suspend fun emit(value: T) {
        mutableSharedFlow.emit(value)
    }

    @ExperimentalCoroutinesApi
    override fun resetReplayCache() {
        mutableSharedFlow.resetReplayCache()
    }

    override fun tryEmit(value: T) = mutableSharedFlow.tryEmit(value)

    override val replayCache: List<T> = mutableSharedFlow.replayCache

    override suspend fun collect(collector: FlowCollector<T>): Nothing {
        mutableSharedFlow.collect(collector)
    }
}

class ConcreteMutableStateFlow<T>(private val mutableSharedFlow: MutableStateFlow<T>) : MutableStateFlow<T>, ConcreteMutableSharedFlow<T>(mutableSharedFlow) {
    override var value: T
        get() = mutableSharedFlow.value
        set(value) {
            mutableSharedFlow.value = value
        }

    override fun compareAndSet(expect: T, update: T) = mutableSharedFlow.compareAndSet(expect, update)
}
