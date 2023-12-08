package com.mirego.kmp.boilerplate.viewmodel.screen

import com.mirego.trikot.viewmodels.declarative.components.VMDButtonViewModel
import com.mirego.trikot.viewmodels.declarative.components.VMDTextViewModel
import com.mirego.trikot.viewmodels.declarative.content.VMDTextContent
import com.mirego.trikot.viewmodels.declarative.viewmodel.VMDLifecycleViewModel
import com.mirego.trikot.viewmodels.declarative.viewmodel.VMDViewModel

interface Screen1ViewModel : VMDViewModel {
    val title: VMDTextViewModel

    val closeButton: VMDButtonViewModel<VMDTextContent>

    val pushButton: VMDButtonViewModel<VMDTextContent>

    val modalButton: VMDButtonViewModel<VMDTextContent>

    val popToRoot: VMDButtonViewModel<VMDTextContent>

    val popToScreen3Inclusive: VMDButtonViewModel<VMDTextContent>

    val popToScreen3Exclusive: VMDButtonViewModel<VMDTextContent>
}
