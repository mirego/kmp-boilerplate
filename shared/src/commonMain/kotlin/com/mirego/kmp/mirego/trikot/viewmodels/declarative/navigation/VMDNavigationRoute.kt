package com.mirego.kmp.mirego.trikot.viewmodels.declarative.navigation

import com.mirego.trikot.viewmodels.declarative.viewmodel.VMDViewModel

interface VMDNavigationRoute {
    val viewModel: VMDViewModel?
        get() = null
    val name: String
    val resetBlock: () -> Unit
}
