package com.mirego.kmp.boilerplate.utils

import com.mirego.kmp.boilerplate.extension.prioritiseData
import com.mirego.trikot.datasources.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf

typealias StateData<V> = DataState<V, Throwable>

fun <V> stateDataPending(value: V? = null): StateData<V> = DataState.pending(value)
fun <V> stateDataData(value: V): StateData<V> = DataState.data(value)
fun <V> stateDataError(error: Throwable, value: V? = null): StateData<V> = DataState.error(error, value)

fun <T, R> Flow<StateData<out T>>.flatMapLatestStateData(getValueTransform: suspend (data: T) -> Flow<StateData<R>>): Flow<StateData<R>> {
    return flatMapLatest {
        when (val prioritiseData = it.prioritiseData()) {
            is DataState.Data -> getValueTransform(prioritiseData.value)
            is DataState.Pending -> flowOf(DataState.Pending())
            is DataState.Error -> flowOf(DataState.Error(prioritiseData.error))
        }
    }
}
