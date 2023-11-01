package com.mirego.kmp.mirego.trikot.viewmodels.declarative.navigation

import com.mirego.trikot.viewmodels.declarative.viewmodel.VMDLifecycleViewModelImpl
import kotlinx.coroutines.CoroutineScope

open class VMDBaseScreenViewModelImpl(
    private val onTrackScreenView: () -> Unit,
    coroutineScope: CoroutineScope
) : VMDBaseScreenViewModel, VMDLifecycleViewModelImpl(coroutineScope) {

    override fun onAppearFirst(coroutineScope: CoroutineScope) {
        super.onAppearFirst(coroutineScope)
        onTrackScreenView()
    }
}
