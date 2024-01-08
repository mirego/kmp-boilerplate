package com.mirego.kmp.boilerplate.bootstrap

import com.mirego.kmp.boilerplate.viewmodel.application.ApplicationViewModel
import com.mirego.kmp.boilerplate.viewmodel.navigation.DemoNavigationManager
import com.mirego.kmp.boilerplate.viewmodel.navigation.DemoNavigationManagerImpl
import com.mirego.kmp.boilerplate.viewmodel.navigation.vmd.CoroutineScopeManagerImpl
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.context.startKoin
import org.koin.core.parameter.parametersOf

class Bootstrapper : KoinComponent {
    private val coroutineScopeManager = CoroutineScopeManagerImpl()

    fun initDependencies(bootstrap: Bootstrap) = startKoin {
        configureKoin(bootstrap)
    }

    fun applicationViewModel(): ApplicationViewModel = get {
        parametersOf(coroutineScopeManager, coroutineScopeManager.createMainThreadCoroutineScope(), DemoNavigationManagerImpl(coroutineScopeManager))
    }
}
