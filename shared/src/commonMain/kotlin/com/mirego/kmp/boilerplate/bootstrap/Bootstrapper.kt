package com.mirego.kmp.boilerplate.bootstrap

import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin

class Bootstrapper(
    val bootstrap: Bootstrap
) : KoinComponent {
    fun initDependencies() = startKoin {
        configureKoin(bootstrap)
    }
}
