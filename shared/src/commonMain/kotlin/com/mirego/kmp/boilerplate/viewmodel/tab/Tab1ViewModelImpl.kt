package com.mirego.kmp.boilerplate.viewmodel.tab

import com.mirego.kmp.boilerplate.viewmodel.dialog.DialogButtonData
import com.mirego.kmp.boilerplate.viewmodel.dialog.DialogNavigationData
import com.mirego.kmp.boilerplate.viewmodel.navigation.DemoNavigationManager
import com.mirego.kmp.boilerplate.viewmodel.navigation.DemoNavigationRoute
import com.mirego.kmp.boilerplate.viewmodel.navigation.NavigationAction
import com.mirego.trikot.viewmodels.declarative.viewmodel.VMDViewModelImpl
import com.mirego.trikot.viewmodels.declarative.viewmodel.buttonWithText
import com.mirego.trikot.viewmodels.declarative.viewmodel.text
import kotlinx.coroutines.CoroutineScope
import org.koin.core.annotation.Factory

@Factory
class Tab1ViewModelImpl(
    override val navigationManager: DemoNavigationManager,
    coroutineScope: CoroutineScope
) : Tab1ViewModel, VMDViewModelImpl(coroutineScope) {

    override val title = "Tab 1"

    override val dialogResult = text("No result")

    override val pushButton = buttonWithText("Push") {
        setAction {
            navigationManager.push(DemoNavigationRoute.Screen1(), locally = true)
        }
    }

    override val modalButton = buttonWithText("Modal") {
        setAction {
            navigationManager.push(DemoNavigationRoute.Screen2())
        }
    }

    override val dialogButton = buttonWithText("Dialog") {
        setAction {
            navigationManager.push(DemoNavigationRoute.Dialog(buildDialogNavigationData()))
        }
    }

    override val openExternalUrl = buttonWithText("External url") {
        setAction {
            navigationManager.handleAction(NavigationAction.ExternalUrl("https://www.mirego.com"))
        }
    }

    private fun buildDialogNavigationData() = DialogNavigationData(
        title = "Test Dialog",
        message = "Pick a choice",
        buttons = listOf(
            DialogButtonData(
                title = "Choice 1",
                action = {
                    dialogResult.text = "Choice 1"
                }
            ),
            DialogButtonData(
                title = "Choice 2",
                action = {
                    dialogResult.text = "Choice 2"
                }
            )
        )
    )
}
