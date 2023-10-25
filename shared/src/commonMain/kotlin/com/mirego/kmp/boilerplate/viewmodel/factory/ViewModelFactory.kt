package com.mirego.kmp.boilerplate.viewmodel.factory

import com.mirego.kmp.boilerplate.viewmodel.root.RootViewModel
import kotlinx.coroutines.CoroutineScope

interface ViewModelFactory {
    fun createRoot(coroutineScope: CoroutineScope): RootViewModel
}
