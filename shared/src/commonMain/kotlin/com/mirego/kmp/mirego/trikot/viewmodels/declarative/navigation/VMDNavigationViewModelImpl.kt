package com.mirego.kmp.mirego.trikot.viewmodels.declarative.navigation

import com.mirego.trikot.foundation.concurrent.AtomicReference
import com.mirego.trikot.viewmodels.declarative.util.CoroutineScopeProvider
import com.mirego.trikot.viewmodels.declarative.viewmodel.internal.VMDFlowProperty
import com.mirego.trikot.viewmodels.declarative.viewmodel.internal.emit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import org.koin.core.component.KoinComponent

abstract class VMDNavigationViewModelImpl<ROUTE : VMDNavigationRoute>(
    onTrackScreenView: () -> Unit,
    coroutineScope: CoroutineScope
) : VMDNavigationViewModel<ROUTE>, VMDBaseScreenViewModelImpl(onTrackScreenView = onTrackScreenView, coroutineScope = coroutineScope), KoinComponent {

    private val currentCoroutineScope = AtomicReference<CoroutineScope?>(null)

    private var isNavigationBound = false

    private val navigationRouteDelegate = emit(null as ROUTE?, this, coroutineScope)
    override var navigationRoute: ROUTE? by navigationRouteDelegate

    fun cancelAndCreateCoroutineScope() = currentCoroutineScope.value.let { currentScope ->
        val newScope = createCoroutineScope()
        currentCoroutineScope.setOrThrow(currentScope, newScope)

        currentScope?.cancel()

        newScope
    }

    private fun createCoroutineScope() = CoroutineScopeProvider.provideMainWithSuperviserJob()

    fun updateRoute(route: ROUTE) {
        navigationRoute = route
    }

    fun resetRoute() {
        if (navigationRoute != null) {
            navigationRoute = null
            cancelAndCreateCoroutineScope()
        }
    }

    override val propertyMapping: Map<String, VMDFlowProperty<*>> by lazy {
        super.propertyMapping.toMutableMap().also {
            it[::navigationRoute.name] = navigationRouteDelegate
        }
    }
}
