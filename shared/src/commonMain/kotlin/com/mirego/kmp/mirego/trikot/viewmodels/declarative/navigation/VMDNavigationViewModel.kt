package com.mirego.kmp.mirego.trikot.viewmodels.declarative.navigation

import com.mirego.trikot.viewmodels.declarative.viewmodel.VMDViewModel

interface VMDNavigationViewModel<ROUTE : VMDNavigationRoute> : VMDViewModel, VMDBaseScreenViewModel {
    val navigationRoute: ROUTE?
}
