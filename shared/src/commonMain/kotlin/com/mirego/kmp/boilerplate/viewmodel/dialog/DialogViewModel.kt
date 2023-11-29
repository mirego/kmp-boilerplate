package com.mirego.kmp.boilerplate.viewmodel.dialog

import com.mirego.trikot.viewmodels.declarative.components.VMDButtonViewModel
import com.mirego.trikot.viewmodels.declarative.content.VMDTextContent
import com.mirego.trikot.viewmodels.declarative.viewmodel.VMDViewModel

interface DialogViewModel : VMDViewModel {
    val closeButton: VMDButtonViewModel<VMDTextContent>

    val title: String
    val message: String

    val button: List<VMDButtonViewModel<VMDTextContent>>
}
