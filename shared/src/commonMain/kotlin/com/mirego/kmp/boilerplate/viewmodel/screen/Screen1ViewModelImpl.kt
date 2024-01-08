package com.mirego.kmp.boilerplate.viewmodel.screen

import com.mirego.kmp.boilerplate.viewmodel.navigation.DemoNavigationManager
import com.mirego.kmp.boilerplate.viewmodel.navigation.DemoNavigationRoute
import com.mirego.trikot.viewmodels.declarative.viewmodel.VMDViewModelImpl
import com.mirego.trikot.viewmodels.declarative.viewmodel.buttonWithText
import com.mirego.trikot.viewmodels.declarative.viewmodel.text
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.koin.core.annotation.Factory

@Factory
class Screen1ViewModelImpl(
    uniqueId: String,
    private val navigationManager: DemoNavigationManager,
    coroutineScope: CoroutineScope
) : Screen1ViewModel, VMDViewModelImpl(coroutineScope) {

    private val counter = MutableStateFlow(0)

    override val title = text().apply {
        bindText(counter.map { "Screen 1 - $uniqueId\n$it" })
    }

    override val closeButton = buttonWithText("Close") {
        setAction {
            navigationManager.pop()
        }
    }

    override val pushButton = buttonWithText("Push") {
        setAction {
            navigationManager.push(DemoNavigationRoute.Screen1(), locally = true)
        }
    }

    override val modalButton = buttonWithText("Modal") {
        setAction {
            navigationManager.push(DemoNavigationRoute.Screen3())
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

    init {
        coroutineScope.launch {
            while (true) {
                delay(1000)
                counter.value = counter.value + 1
            }
        }
    }
}
