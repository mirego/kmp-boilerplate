package com.mirego.kmp.boilerplate.viewmodel.application

import com.mirego.kmp.boilerplate.viewmodel.factory.ViewModelFactory
import com.mirego.trikot.viewmodels.declarative.viewmodel.VMDViewModelImpl
import kotlinx.coroutines.CoroutineScope
import org.koin.core.annotation.Factory

@Factory
class ApplicationViewModelImpl(
    viewModelFactory: ViewModelFactory,
    coroutineScope: CoroutineScope
) : ApplicationViewModel, VMDViewModelImpl(coroutineScope) {
    override val rootViewModel = viewModelFactory.createRoot(coroutineScope)
}
