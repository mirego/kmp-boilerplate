package com.mirego.kmp.boilerplate.viewmodel.factory

import com.mirego.kmp.boilerplate.viewmodel.dialog.DialogNavigationData
import com.mirego.kmp.boilerplate.viewmodel.dialog.DialogViewModel
import com.mirego.kmp.boilerplate.viewmodel.navigation.DemoNavigationManager
import com.mirego.kmp.boilerplate.viewmodel.screen.Screen1ViewModel
import com.mirego.kmp.boilerplate.viewmodel.screen.Screen2ViewModel
import com.mirego.kmp.boilerplate.viewmodel.screen.Screen3ViewModel
import com.mirego.kmp.boilerplate.viewmodel.tab.Tab1ViewModel
import com.mirego.kmp.boilerplate.viewmodel.tab.Tab2ViewModel
import kotlinx.coroutines.CoroutineScope

interface ViewModelFactory {
    fun createTab1(navigationManager: DemoNavigationManager, coroutineScope: CoroutineScope): Tab1ViewModel

    fun createTab2(navigationManager: DemoNavigationManager, coroutineScope: CoroutineScope): Tab2ViewModel

    fun createScreen1(uniqueId :String, navigationManager: DemoNavigationManager, coroutineScope: CoroutineScope): Screen1ViewModel

    fun createScreen2(navigationManager: DemoNavigationManager, coroutineScope: CoroutineScope): Screen2ViewModel

    fun createScreen3(navigationManager: DemoNavigationManager, coroutineScope: CoroutineScope): Screen3ViewModel

    fun createDialog(navigationData: DialogNavigationData, navigationManager: DemoNavigationManager, coroutineScope: CoroutineScope): DialogViewModel
}
