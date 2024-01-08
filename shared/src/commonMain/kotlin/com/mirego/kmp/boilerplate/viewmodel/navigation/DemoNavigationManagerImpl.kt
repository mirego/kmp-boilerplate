package com.mirego.kmp.boilerplate.viewmodel.navigation

import com.mirego.kmp.boilerplate.viewmodel.factory.ViewModelFactory
import com.mirego.kmp.boilerplate.viewmodel.navigation.vmd.CoroutineScopeManager
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DemoNavigationManagerImpl(
    coroutineScopeManager: CoroutineScopeManager,
    parentNavigationManager: DemoNavigationManager? = null
) : DemoNavigationManager(
    coroutineScopeManager = coroutineScopeManager,
    parentNavigationManager = parentNavigationManager
), KoinComponent {

    private val viewModelFactory: ViewModelFactory by inject()

    override fun createScreen1(route: DemoNavigationRoute.Screen1) =
        viewModelFactory.createScreen1(route.uniqueId, this, coroutineScopeManager.createMainThreadCoroutineScope())

    override fun createScreen2(route: DemoNavigationRoute.Screen2) =
        viewModelFactory.createScreen2(this, coroutineScopeManager.createMainThreadCoroutineScope())

    override fun createScreen3(route: DemoNavigationRoute.Screen3) =
        viewModelFactory.createScreen3(this, coroutineScopeManager.createMainThreadCoroutineScope())

    override fun createDialog(route: DemoNavigationRoute.Dialog) =
        viewModelFactory.createDialog(route.navigationData, this, coroutineScopeManager.createMainThreadCoroutineScope())
}
