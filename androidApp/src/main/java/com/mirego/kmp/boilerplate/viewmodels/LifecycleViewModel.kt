package com.mirego.kmp.boilerplate.viewmodels

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory


/**
 * Convenience viewModel builder which creates the common ViewModel using the provided initializer
 * and wraps it into an androidx.lifecycle.ViewModel() for proper cancellation.
 */
@Composable
inline fun <reified VM : ViewModel> lifecycleViewModel(crossinline initializer: () -> VM): VM {
    val factory = viewModelFactory {
        initializer {
            LifecycleViewModel(vm = initializer())
        }
    }
    return viewModel<LifecycleViewModel<VM>>(factory = factory).vm
}

/**
 * Wraps our common ViewModel into an androidx.lifecycle.ViewModel() to cancel work when cleared.
 */
class LifecycleViewModel<VM : ViewModel>(val vm: VM) : androidx.lifecycle.ViewModel() {
    override fun onCleared() = vm.cancel()
}
