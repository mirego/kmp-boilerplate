package com.mirego.kmp.boilerplate.viewmodel.navigation

import com.mirego.kmp.boilerplate.viewmodel.dialog.DialogViewModel
import com.mirego.kmp.boilerplate.viewmodel.navigation.vmd.CoroutineScopeManager
import com.mirego.kmp.boilerplate.viewmodel.navigation.vmd.VMDNavigationManagerImpl
import com.mirego.kmp.boilerplate.viewmodel.screen.Screen1ViewModel
import com.mirego.kmp.boilerplate.viewmodel.screen.Screen2ViewModel
import com.mirego.kmp.boilerplate.viewmodel.screen.Screen3ViewModel

abstract class DemoNavigationManager(
    coroutineScopeManager: CoroutineScopeManager,
    parentNavigationManager: DemoNavigationManager?
) : VMDNavigationManagerImpl<DemoNavigationRoute, NavigationAction>(coroutineScopeManager, parentNavigationManager) {

    abstract fun createScreen1(route: DemoNavigationRoute.Screen1): Screen1ViewModel

    abstract fun createScreen2(route: DemoNavigationRoute.Screen2): Screen2ViewModel

    abstract fun createScreen3(route: DemoNavigationRoute.Screen3): Screen3ViewModel

    abstract fun createDialog(route: DemoNavigationRoute.Dialog): DialogViewModel
}
