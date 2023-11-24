package com.mirego.kmp.boilerplate.trikot.viewmodels.declarative.compose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.mirego.trikot.viewmodels.declarative.viewmodel.VMDViewModel

/**
 * Use this method to get the initial VMDViewModel. This will make sure that it survives Activity recreation by wrapping it in a androidx.lifecycle.ViewModel
 */
@Suppress("UNCHECKED_CAST")
fun <VMD : VMDViewModel> ViewModelStoreOwner.getInitialViewModel(factory: () -> VMD): VMD =
    ViewModelProvider(
        viewModelStore,
        ViewModelProviderFactory(factory)
    )[ViewModelWrapper::class.java].wrappedViewModel as VMD

@Suppress("UNCHECKED_CAST")
private class ViewModelProviderFactory<VMD : VMDViewModel>(private val factory: () -> VMD) : ViewModelProvider.Factory {
    override fun <VM : ViewModel> create(modelClass: Class<VM>): VM {
        return ViewModelWrapper(factory()) as VM
    }
}

class ViewModelWrapper<VMD : VMDViewModel>(val wrappedViewModel: VMD) : ViewModel()
