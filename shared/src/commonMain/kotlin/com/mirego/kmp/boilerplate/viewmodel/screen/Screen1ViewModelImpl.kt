package com.mirego.kmp.boilerplate.viewmodel.screen

import com.mirego.kmp.boilerplate.viewmodel.navigation.DemoNavigationManager
import com.mirego.kmp.boilerplate.viewmodel.navigation.DemoNavigationRoute
import com.mirego.trikot.viewmodels.declarative.viewmodel.VMDViewModelImpl
import com.mirego.trikot.viewmodels.declarative.viewmodel.buttonWithText
import kotlinx.coroutines.CoroutineScope
import org.koin.core.annotation.Factory

@Factory
class Screen1ViewModelImpl(
    private val navigationManager: DemoNavigationManager,
    coroutineScope: CoroutineScope
) : Screen1ViewModel, VMDViewModelImpl(coroutineScope) {
    override val title = "Screen 1"

    override val closeButton = buttonWithText("Close") {
        setAction {
            navigationManager.pop()
        }
    }

    override val pushButton = buttonWithText("Push") {
        setAction {
            navigationManager.push(DemoNavigationRoute.Screen1)
        }
    }

    override val modalButton = buttonWithText("Modal") {
        setAction {
            navigationManager.present(DemoNavigationRoute.Screen3)
        }
    }
}
