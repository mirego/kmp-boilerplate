package com.mirego.kmp.boilerplate.trikot.viewmodels.declarative.compose

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mirego.trikot.viewmodels.declarative.viewmodel.VMDViewModel

/**
 * Use this Composable method to get any viewmodel and make sure they survive Activity recreation.
 */
@Composable
inline fun <reified VMD : VMDViewModel> vmdViewModel(crossinline factory: () -> VMD): VMD {
    val wrapper: ViewModelWrapper<VMD> = viewModel(
        factory = ViewModelProviderFactory {
            factory()
        },
        key = VMD::class.qualifiedName
    )
    return wrapper.wrappedViewModel
}

@Suppress("UNCHECKED_CAST")
class ViewModelProviderFactory<VMD : VMDViewModel>(private val factory: () -> VMD) :
    ViewModelProvider.Factory {
    override fun <VM : ViewModel> create(modelClass: Class<VM>): VM {
        return ViewModelWrapper(factory()) as VM
    }
}

class ViewModelWrapper<VMD : VMDViewModel>(val wrappedViewModel: VMD) : ViewModel()
