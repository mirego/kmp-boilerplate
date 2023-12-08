package com.mirego.kmp.boilerplate.viewmodel.screen

import com.mirego.trikot.viewmodels.declarative.components.VMDButtonViewModel
import com.mirego.trikot.viewmodels.declarative.content.VMDTextContent
import com.mirego.trikot.viewmodels.declarative.viewmodel.VMDLifecycleViewModel
import com.mirego.trikot.viewmodels.declarative.viewmodel.VMDViewModel

interface Screen2ViewModel : VMDViewModel {
    val title: String

    val closeButton: VMDButtonViewModel<VMDTextContent>

    val modalButton: VMDButtonViewModel<VMDTextContent>
}
