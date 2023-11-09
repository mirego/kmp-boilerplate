package com.mirego.kmp.boilerplate.viewmodels

import com.mirego.kmp.boilerplate.usecases.UseCaseFactory
import com.mirego.kmp.boilerplate.viewmodels.example.ExampleViewModel
import com.mirego.kmp.boilerplate.viewmodels.example.ExampleViewModelImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class ViewModelFactory(
    private val useCaseFactory: UseCaseFactory
) {
    fun exampleViewModel(
        coroutineScope: CoroutineScope = defaultCoroutineScope()
    ): ExampleViewModel = ExampleViewModelImpl(
        coroutineScope,
        useCaseFactory.exampleUseCase
    )

    /**
     * Provides a default coroutine scope for iOS viewModels
     */
    private fun defaultCoroutineScope(): CoroutineScope =
        CoroutineScope(Dispatchers.Main.immediate + SupervisorJob())
}
