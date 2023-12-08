package com.mirego.kmp.boilerplate.viewmodel.factory

import com.mirego.kmp.boilerplate.viewmodel.dialog.DialogNavigationData
import com.mirego.kmp.boilerplate.viewmodel.dialog.DialogViewModel
import com.mirego.kmp.boilerplate.viewmodel.navigation.DemoNavigationManager
import com.mirego.kmp.boilerplate.viewmodel.screen.Screen1ViewModel
import com.mirego.kmp.boilerplate.viewmodel.screen.Screen2ViewModel
import com.mirego.kmp.boilerplate.viewmodel.screen.Screen3ViewModel
import com.mirego.kmp.boilerplate.viewmodel.tab.Tab1ViewModel
import com.mirego.kmp.boilerplate.viewmodel.tab.Tab2ViewModel
import com.mirego.trikot.kword.I18N
import kotlinx.coroutines.CoroutineScope
import org.koin.core.annotation.Single
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf

@Single
class ViewModelFactoryImpl : ViewModelFactory, KoinComponent {
    override fun createTab1(
        navigationManager: DemoNavigationManager,
        coroutineScope: CoroutineScope
    ): Tab1ViewModel = get {
        parametersOf(navigationManager, coroutineScope)
    }

    override fun createTab2(
        navigationManager: DemoNavigationManager,
        coroutineScope: CoroutineScope
    ): Tab2ViewModel = get {
        parametersOf(navigationManager, coroutineScope)
    }

    override fun createScreen1(uniqueId:String, navigationManager: DemoNavigationManager, coroutineScope: CoroutineScope): Screen1ViewModel {
        return get {
            parametersOf(uniqueId, navigationManager, coroutineScope)
        }
    }

    override fun createScreen2(navigationManager: DemoNavigationManager, coroutineScope: CoroutineScope): Screen2ViewModel = get {
        parametersOf(navigationManager, coroutineScope)
    }

    override fun createScreen3(navigationManager: DemoNavigationManager, coroutineScope: CoroutineScope): Screen3ViewModel = get {
        parametersOf(navigationManager, coroutineScope)
    }

    override fun createDialog(
        navigationData: DialogNavigationData,
        navigationManager: DemoNavigationManager,
        coroutineScope: CoroutineScope
    ): DialogViewModel = get {
        parametersOf(navigationManager, navigationData, coroutineScope)
    }
}
