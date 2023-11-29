package com.mirego.kmp.boilerplate.viewmodel.navigation

import com.mirego.kmp.boilerplate.viewmodel.navigation.vmd.VMDNavigationResult

sealed interface DemoNavigationResult : VMDNavigationResult {
    data object Empty : DemoNavigationResult
    data class DialogResult(val buttonIdTapped: String) : DemoNavigationResult
}
