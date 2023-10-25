package com.mirego.kmp.boilerplate.viewmodel.application

import com.mirego.kmp.boilerplate.viewmodel.root.RootViewModel
import com.mirego.trikot.viewmodels.declarative.viewmodel.VMDViewModel

interface ApplicationViewModel : VMDViewModel {
    val rootViewModel: RootViewModel
}
