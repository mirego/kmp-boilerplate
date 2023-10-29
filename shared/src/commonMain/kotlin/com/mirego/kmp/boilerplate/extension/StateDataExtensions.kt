package com.mirego.kmp.boilerplate.extension

import com.mirego.kmp.boilerplate.utils.StateData
import com.mirego.kmp.boilerplate.utils.stateDataData
import com.mirego.kmp.boilerplate.utils.stateDataError
import com.mirego.kmp.boilerplate.utils.stateDataPending
import com.mirego.trikot.datasources.DataState
import com.mirego.trikot.datasources.flow.FlowDataSourceExpiringValue
import com.mirego.trikot.foundation.date.Date
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun <T> Flow<StateData<out T?>>.mapToErrorIfValueNull(): Flow<StateData<T>> {
    return map { dataState ->
        when (dataState) {
            is DataState.Pending -> stateDataPending(dataState.value)
            is DataState.Data -> dataState.value?.let {
                stateDataData(it)
            } ?: stateDataError(Throwable(), dataState.value)

            is DataState.Error -> stateDataError(dataState.error, dataState.value)
        }
    }
}

fun <T, E : Throwable> DataState<T, E>.prioritiseData(): DataState<T, E> = when (this) {
    is DataState.Pending -> value?.let { DataState.data(it) } ?: DataState.pending()
    is DataState.Data -> this
    is DataState.Error -> value?.let { DataState.data(it) } ?: DataState.error(error)
}

fun <T> Flow<StateData<T>>.moveAllDataToData(): Flow<StateData<T>> {
    return map { stateState ->
        when (stateState) {
            is DataState.Data -> stateState
            is DataState.Error -> stateState.value?.let { stateDataData(it) } ?: stateState
            is DataState.Pending -> stateState.value?.let { stateDataData(it) } ?: stateState
        }
    }
}

fun <T> Flow<StateData<T?>>.mapNullDataToError(): Flow<StateData<T>> = map { stateData ->
    when (stateData) {
        is DataState.Pending -> stateDataPending(stateData.value)
        is DataState.Data -> {
            stateData.value?.let { data ->
                stateDataData(data)
            } ?: stateDataError(Throwable(), stateData.value)
        }

        is DataState.Error -> stateDataError(stateData.error, stateData.value)
    }
}

fun <T> Flow<StateData<FlowDataSourceExpiringValue<T>>>.removeExpiredValue(extraExpiredMilliseconds: Long = 0): Flow<StateData<T>> {
    return map { dataState ->
        when (dataState) {
            is DataState.Pending -> stateDataPending(dataState.value?.valueIfNotExpired(extraExpiredMilliseconds))

            is DataState.Data -> dataState.value.valueIfNotExpired(extraExpiredMilliseconds)?.let {
                stateDataData(it)
            } ?: stateDataError(Throwable("Data is expired."))

            is DataState.Error -> stateDataError(dataState.error, dataState.value?.valueIfNotExpired(extraExpiredMilliseconds))
        }
    }
}

private fun <T> FlowDataSourceExpiringValue<T>.valueIfNotExpired(extraExpiredMilliseconds: Long) = if ((expiredEpoch + extraExpiredMilliseconds) < Date.now.epoch) {
    null
} else {
    value
}
