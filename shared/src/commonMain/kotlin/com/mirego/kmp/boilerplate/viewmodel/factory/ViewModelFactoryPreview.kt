package com.mirego.kmp.boilerplate.viewmodel.factory

import com.mirego.kmp.boilerplate.viewmodel.root.RootViewModelImpl
import com.mirego.kmp.boilerplate.viewmodel.application.ApplicationViewModelImpl
import com.mirego.trikot.kword.I18N
import com.mirego.trikot.viewmodels.declarative.util.CoroutineScopeProvider
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope

@Suppress("unused", "MemberVisibilityCanBePrivate")
class ViewModelFactoryPreview(
    private val i18N: I18N
) : ViewModelFactory {

    fun createCoroutineScope() = CoroutineScopeProvider.provideMainWithSuperviserJob(
        CoroutineExceptionHandler { _, exception ->
            println("CoroutineExceptionHandler got $exception")
        }
    )

    fun createApplication() = ApplicationViewModelImpl(
        this,
        createCoroutineScope()
    )

    override fun createRoot(coroutineScope: CoroutineScope) = createRoot()

    fun createRoot() = RootViewModelImpl(
        i18N = i18N,
        viewModelFactory = this,
        coroutineScope = createCoroutineScope()
    )
}
