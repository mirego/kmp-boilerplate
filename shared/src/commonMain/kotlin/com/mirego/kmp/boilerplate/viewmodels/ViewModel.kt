package com.mirego.kmp.boilerplate.viewmodels

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel

interface ViewModel {
    fun cancel()
}

abstract class BaseViewModel(private val coroutineScope: CoroutineScope) : ViewModel {
    final override fun cancel() = coroutineScope.cancel()
}
