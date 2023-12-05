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
            navigationManager.push(DemoNavigationRoute.Screen1())
        }
    }

    override val modalButton = buttonWithText("Modal") {
        setAction {
            navigationManager.push(DemoNavigationRoute.Screen3(), prioritizeParent = true)
        }
    }

    override val popToRoot = buttonWithText("Pop to root") {
        setAction {
            navigationManager.popToRoot()
        }
    }

    override val popToScreen3Inclusive = buttonWithText("Pop to screen 3 inclusive") {
        setAction {
            navigationManager.popToName(DemoNavigationRoute.Screen3().name, inclusive = true)
        }
    }

    override val popToScreen3Exclusive = buttonWithText("Pop to screen 3 exclusive") {
        setAction {
            navigationManager.popToName(DemoNavigationRoute.Screen3().name, inclusive = false)
        }
    }
}
