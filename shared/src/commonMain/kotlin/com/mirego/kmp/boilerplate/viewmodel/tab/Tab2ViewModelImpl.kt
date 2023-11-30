package com.mirego.kmp.boilerplate.viewmodel.tab

import com.mirego.kmp.boilerplate.viewmodel.navigation.DemoNavigationManager
import com.mirego.kmp.boilerplate.viewmodel.navigation.DemoNavigationRoute
import com.mirego.trikot.viewmodels.declarative.viewmodel.VMDViewModelImpl
import com.mirego.trikot.viewmodels.declarative.viewmodel.buttonWithText
import kotlinx.coroutines.CoroutineScope
import org.koin.core.annotation.Factory

@Factory
class Tab2ViewModelImpl(
    override val navigationManager: DemoNavigationManager,
    coroutineScope: CoroutineScope
) : Tab2ViewModel, VMDViewModelImpl(coroutineScope) {
    override val title = "Tab 2"

    override val pushButton = buttonWithText("Push") {
        setAction {
            navigationManager.push(DemoNavigationRoute.Screen1())
        }
    }

    override val modalButton= buttonWithText("Modal") {
        setAction {
            navigationManager.present(DemoNavigationRoute.Screen3())
        }
    }
}
