package com.mirego.kmp.boilerplate.viewmodels.extensions

import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.StateFlow

fun <T, R> StateFlow<T>.mapState(mapper: (T) -> R): StateFlow<R> = MappedStateFlow(this, mapper)

class MappedStateFlow<T, R>(
    private val source: StateFlow<T>,
    private val mapper: (T) -> R
) : StateFlow<R> {

    override val value: R
        get() = mapper(source.value)

    override val replayCache: List<R>
        get() = source.replayCache.map(mapper)

    override suspend fun collect(collector: FlowCollector<R>): Nothing {
        source.collect { value ->
            collector.emit(mapper(value))
        }
    }
}
