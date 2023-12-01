package com.mirego.kmp.boilerplate.viewmodel.dialog

import com.mirego.kmp.boilerplate.viewmodel.navigation.DemoNavigationManager
import com.mirego.kmp.boilerplate.viewmodel.navigation.DemoNavigationResult
import com.mirego.trikot.viewmodels.declarative.viewmodel.VMDViewModelImpl
import com.mirego.trikot.viewmodels.declarative.viewmodel.buttonWithText
import kotlinx.coroutines.CoroutineScope

class DialogViewModelImpl(
    private val navigationManager: DemoNavigationManager,
    navigationData: DialogNavigationData,
    coroutineScope: CoroutineScope
) : DialogViewModel, VMDViewModelImpl(coroutineScope) {

    override val closeButton = buttonWithText("Close") {
        navigationManager.pop()
    }

    override val title = navigationData.title

    override val message = navigationData.message

    override val button = navigationData.buttons
        .map { button ->
            buttonWithText(text = button.title) {
                setAction {
                    navigationManager.pop(DemoNavigationResult.DialogResult(button.id))
                }
            }
        }
}
