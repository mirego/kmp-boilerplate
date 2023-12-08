package com.mirego.kmp.boilerplate.viewmodel.tab

import com.mirego.kmp.boilerplate.viewmodel.navigation.DemoNavigationManager
import com.mirego.trikot.viewmodels.declarative.components.VMDButtonViewModel
import com.mirego.trikot.viewmodels.declarative.components.VMDTextViewModel
import com.mirego.trikot.viewmodels.declarative.content.VMDTextContent
import com.mirego.trikot.viewmodels.declarative.viewmodel.VMDLifecycleViewModel
import com.mirego.trikot.viewmodels.declarative.viewmodel.VMDViewModel

interface Tab1ViewModel : VMDViewModel {
    val navigationManager: DemoNavigationManager

    val title: String
    val dialogResult: VMDTextViewModel

    val pushButton: VMDButtonViewModel<VMDTextContent>
    val modalButton: VMDButtonViewModel<VMDTextContent>
    val dialogButton: VMDButtonViewModel<VMDTextContent>
    val openExternalUrl: VMDButtonViewModel<VMDTextContent>
}
