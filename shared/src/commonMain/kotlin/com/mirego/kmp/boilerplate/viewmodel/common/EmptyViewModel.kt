package com.mirego.kmp.boilerplate.viewmodel.common

import com.mirego.trikot.viewmodels.declarative.components.VMDButtonViewModel
import com.mirego.trikot.viewmodels.declarative.components.VMDImageViewModel
import com.mirego.trikot.viewmodels.declarative.components.VMDTextViewModel
import com.mirego.trikot.viewmodels.declarative.content.VMDTextContent
import com.mirego.trikot.viewmodels.declarative.viewmodel.VMDViewModel

interface EmptyViewModel : VMDViewModel {
    val icon: VMDImageViewModel
    val title: VMDTextViewModel
    val message: VMDTextViewModel
    val actionButton: VMDButtonViewModel<VMDTextContent>?
    val secondaryActionButton: VMDButtonViewModel<VMDTextContent>?
}
