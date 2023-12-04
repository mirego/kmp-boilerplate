package com.mirego.kmp.boilerplate.viewmodel.screen

import com.mirego.trikot.viewmodels.declarative.components.VMDButtonViewModel
import com.mirego.trikot.viewmodels.declarative.content.VMDTextContent
import com.mirego.trikot.viewmodels.declarative.viewmodel.VMDViewModel

interface Screen3ViewModel: VMDViewModel {
    val title: String

    val closeButton: VMDButtonViewModel<VMDTextContent>

    val pushButton: VMDButtonViewModel<VMDTextContent>
}