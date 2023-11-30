package com.mirego.kmp.boilerplate.viewmodel.navigation.vmd

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

data class NavigationItem<T : VMDNavigationRoute, R : VMDNavigationResult>(
    val route: T,
    val coroutineScope: CoroutineScope,
    val closeAction: (R?) -> Unit
)

abstract class VMDNavigationManagerImpl<ROUTE : VMDNavigationRoute, RESULT : VMDNavigationResult>(
    private val coroutineScopeManager: CoroutineScopeManager,
    private val parentNavigationManager: VMDNavigationManager<ROUTE, RESULT>? = null
) : VMDNavigationManager<ROUTE, RESULT>() {

    private val internalRouteList: MutableStateFlow<List<NavigationItem<ROUTE, RESULT>>> =
        MutableStateFlow(emptyList())

    override val navigationItemList: Flow<List<NavigationItem<ROUTE, RESULT>>>
        get() = internalRouteList

    override fun present(route: ROUTE, closeAction: (RESULT?) -> Unit) {
        if (parentNavigationManager != null) {
            parentNavigationManager.present(route, closeAction)
            return
        }

        internalPush(route, closeAction)
    }

    override fun push(route: ROUTE, closeAction: (RESULT?) -> Unit) {
        internalPush(route, closeAction)
    }

    private fun internalPush(route: ROUTE, closeAction: (RESULT?) -> Unit) {
        val newItem = NavigationItem<ROUTE, RESULT>(
            route = route,
            coroutineScope = coroutineScopeManager.createCoroutineScope(),
            closeAction = {
                closeAction(it)
            },
        )
        val newList = internalRouteList.value.toMutableList().apply {
            add(newItem)
        }
        internalRouteList.value = newList
        listener?.push(route)
        println("HUGO DEBUG: pushing ${route.name} count: ${internalRouteList.value.size}")
    }

    override fun pop(result: RESULT?) {
        internalPop(result, callListener = true)
    }

    private fun internalPop(result: RESULT?, callListener: Boolean) {
        val newList = internalRouteList.value.toMutableList().apply {
            removeLastOrNull()?.let { removedItem ->
                removedItem.coroutineScope.cancel()
                removedItem.closeAction(result)
            }
        }
        internalRouteList.value = newList
        println("HUGO DEBUG: popping new count: ${internalRouteList.value.size}")

        if (callListener) {
            listener?.pop()
        }
    }

    override fun popTo(route: ROUTE, included: Boolean) {
        internalPopTo(route, included = included, callListener = true)
    }

    private fun internalPopTo(route: ROUTE, included: Boolean, callListener: Boolean) {
        println("HUGO DEBUG: internalPopTo start count: ${internalRouteList.value.size}")
        val index = internalRouteList.value.indexOfLast {
            it.route.name == route.name
        }
        if (index != -1 && internalRouteList.value.isNotEmpty()) {
            val effectiveIndex = index + if (included) 0 else 1
            (effectiveIndex until internalRouteList.value.size).forEach {
                val item = internalRouteList.value[it]
                item.coroutineScope.cancel()
                item.closeAction(null)
            }

            internalRouteList.value = internalRouteList.value.slice(0..< effectiveIndex).toMutableList()
            println("HUGO DEBUG: internalPopTo final count: ${internalRouteList.value.size}")
            if (callListener) {
                listener?.popTo(route)
            }
        }
    }

    override fun popped(route: ROUTE) {
        val routeIndex = internalRouteList.value.indexOfFirst {
            it.route.uniqueId == route.uniqueId
        }
        if (routeIndex != -1) {
            internalPopTo(route, included = true, callListener = false)
        }
    }

    protected fun getCoroutineScope(route: ROUTE): CoroutineScope = internalRouteList.value
        .lastOrNull { it.route.uniqueId == route.uniqueId }
        ?.coroutineScope ?: coroutineScopeManager.createCoroutineScope()
}
