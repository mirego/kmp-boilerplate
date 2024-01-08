package com.mirego.kmp.boilerplate.viewmodel.screen

import com.mirego.kmp.boilerplate.viewmodel.dialog.DialogButtonData
import com.mirego.kmp.boilerplate.viewmodel.dialog.DialogNavigationData
import com.mirego.kmp.boilerplate.viewmodel.navigation.DemoNavigationManager
import com.mirego.kmp.boilerplate.viewmodel.navigation.DemoNavigationRoute
import com.mirego.trikot.viewmodels.declarative.viewmodel.VMDViewModelImpl
import com.mirego.trikot.viewmodels.declarative.viewmodel.buttonWithText
import kotlinx.coroutines.CoroutineScope
import org.koin.core.annotation.Factory

@Factory
class Screen3ViewModelImpl(
    private val navigationManager: DemoNavigationManager,
    coroutineScope: CoroutineScope
) : Screen3ViewModel, VMDViewModelImpl(coroutineScope) {
    override val title = "Screen 3"

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

    override val dialogButton = buttonWithText("Dialog") {
        setAction {
            navigationManager.push(DemoNavigationRoute.Dialog(buildDialogNavigationData()))
        }
    }

    private fun buildDialogNavigationData() = DialogNavigationData(
        title = "Test Dialog",
        message = "Pick a choice",
        buttons = listOf(
            DialogButtonData(
                title = "Choice 1",
                action = {}
            ),
            DialogButtonData(
                title = "Choice 2",
                action = {}
            )
        )
    )

}
