package com.mirego.kmp.boilerplate.viewmodel.application

import com.mirego.kmp.boilerplate.viewmodel.factory.ViewModelFactory
import com.mirego.kmp.boilerplate.viewmodel.navigation.vmd.CoroutineScopeManager
import com.mirego.kmp.boilerplate.viewmodel.navigation.DemoNavigationManager
import com.mirego.kmp.boilerplate.viewmodel.navigation.DemoNavigationManagerImpl
import com.mirego.trikot.viewmodels.declarative.viewmodel.VMDViewModelImpl
import kotlinx.coroutines.CoroutineScope
import org.koin.core.annotation.Factory

@Factory
class ApplicationViewModelImpl(
    override val navigationManager: DemoNavigationManager,
    coroutineScopeManager: CoroutineScopeManager,
    viewModelFactory: ViewModelFactory,
    coroutineScope: CoroutineScope
) : ApplicationViewModel, VMDViewModelImpl(coroutineScope) {

    override val tab1ViewModel = viewModelFactory.createTab1(
        navigationManager = DemoNavigationManagerImpl(
            coroutineScopeManager = coroutineScopeManager,
            parentNavigationManager = navigationManager
        ),
        coroutineScope = coroutineScope
    )

    override val tab2ViewModel = viewModelFactory.createTab2(
        navigationManager = DemoNavigationManagerImpl(
            coroutineScopeManager = coroutineScopeManager,
            parentNavigationManager = navigationManager
        ),
        coroutineScope = coroutineScope
    )
}
