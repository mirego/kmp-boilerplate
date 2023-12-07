package com.mirego.kmp.boilerplate.viewmodels

import com.mirego.kmp.boilerplate.platform.Platform
import com.mirego.kmp.boilerplate.viewmodels.example.ExampleViewModel
import com.mirego.kmp.boilerplate.viewmodels.example.ExampleViewModelImpl
import com.mirego.konnectivity.Konnectivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class ViewModelFactory {
    private val platform = Platform()
    private val konnectivity = Konnectivity()

    private val defaultCoroutineScope: CoroutineScope
        get() = CoroutineScope(Dispatchers.Main.immediate + SupervisorJob())

    fun example(): ExampleViewModel =
        ExampleViewModelImpl(
            defaultCoroutineScope,
            platform,
            konnectivity,
        )
}
