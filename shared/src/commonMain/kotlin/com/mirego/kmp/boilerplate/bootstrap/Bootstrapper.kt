package com.mirego.kmp.boilerplate.bootstrap

import com.mirego.kmp.boilerplate.viewmodel.application.ApplicationViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.context.startKoin
import org.koin.core.parameter.parametersOf

class Bootstrapper(
    val bootstrap: Bootstrap
) : KoinComponent {
    private val exceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            println("Exception: $throwable")
        }

    private val rootCoroutineScope =
        CoroutineScope(Dispatchers.Main.immediate + SupervisorJob() + exceptionHandler)

    fun initDependencies() = startKoin {
        configureKoin(bootstrap)
    }

    fun applicationViewModel(): ApplicationViewModel = get {
        parametersOf(rootCoroutineScope)
    }
}
