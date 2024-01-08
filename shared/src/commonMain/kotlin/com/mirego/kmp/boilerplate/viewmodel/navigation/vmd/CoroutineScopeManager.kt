package com.mirego.kmp.boilerplate.viewmodel.navigation.vmd

import com.mirego.trikot.viewmodels.declarative.util.CoroutineScopeProvider
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope

interface CoroutineScopeManager {
    fun createMainThreadCoroutineScope(): CoroutineScope
}

class CoroutineScopeManagerImpl : CoroutineScopeManager {
    override fun createMainThreadCoroutineScope() = CoroutineScopeProvider.provideMainWithSuperviserJob(exceptionHandler = exceptionHandler())

    private fun exceptionHandler() = CoroutineExceptionHandler { _, throwable ->
        println("Exception: $throwable")
    }
}
