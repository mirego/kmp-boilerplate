package com.mirego.kmp.boilerplate.viewmodel.navigation.vmd

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

open class VMDNavigationManagerImpl<ROUTE : VMDNavigationRoute, ACTION>(
    protected val coroutineScopeManager: CoroutineScopeManager,
    private val parentNavigationManager: VMDNavigationManager<ROUTE, ACTION>? = null
) : VMDNavigationManager<ROUTE, ACTION>() {

    private val singleThreadCoroutine = coroutineScopeManager.createMainThreadCoroutineScope()

    private val internalRouteList: MutableList<ROUTE> = mutableListOf()

    override fun currentRoutes() = internalRouteList

    @Suppress("UNCHECKED_CAST")
    override fun <T : ROUTE> findRoute(uniqueId: String): T? =
        internalRouteList.firstOrNull { it.uniqueId == uniqueId } as? T

    override fun push(route: ROUTE, locally: Boolean) {
        singleThreadCoroutine.launch {
            if (!locally && parentNavigationManager != null) {
                parentNavigationManager.push(route, locally = locally)
                return@launch
            }

            internalRouteList.add(route)
            listener?.push(route)
        }
    }

    override fun pop() {
        internalPop(callListener = true)
    }

    private fun internalPop(callListener: Boolean) {
        singleThreadCoroutine.launch {
            internalRouteList.removeLastOrNull()

            if (callListener) {
                listener?.pop()
            }
        }
    }

    override fun popToId(uniqueId: String, inclusive: Boolean) {
        internalPopTo(popType = PopType.ById(uniqueId), inclusive = inclusive, callListener = true)
    }

    override fun popToName(name: String, inclusive: Boolean) {
        internalPopTo(popType = PopType.ByName(name), inclusive = inclusive, callListener = true)
    }

    override fun popToRoot() {
        singleThreadCoroutine.launch {
            val firstItem = internalRouteList.firstOrNull() ?: return@launch
            internalPopTo(popType = PopType.ById(firstItem.uniqueId), inclusive = true, callListener = true)
        }
    }

    private fun internalPopTo(popType: PopType, inclusive: Boolean, callListener: Boolean) {
        singleThreadCoroutine.launch {
            val navigationItem = internalRouteList
                .lastOrNull { item ->
                    when (popType) {
                        is PopType.ById -> item.uniqueId == popType.id
                        is PopType.ByName -> item.name == popType.name
                    }
                } ?: return@launch
            val index = internalRouteList.indexOf(navigationItem)

            if (index != -1 && internalRouteList.isNotEmpty()) {
                val effectiveIndex = index + if (inclusive) 0 else 1
                internalRouteList.removeAll(internalRouteList.takeLast(internalRouteList.size - effectiveIndex))
                if (callListener) {
                    listener?.popTo(navigationItem, inclusive = inclusive)
                }
            }
        }
    }

    override fun popped() {
        internalPop(callListener = false)
    }

    override fun handleAction(action: ACTION) {
        parentNavigationManager?.handleAction(action) ?: actionListener?.handleAction(action)
    }

    override fun poppedFrom(route: ROUTE) {
        singleThreadCoroutine.launch {
            val routeIndex = internalRouteList.indexOfFirst {
                it.uniqueId == route.uniqueId
            }
            if (routeIndex != -1) {
                internalPopTo(popType = PopType.ById(route.uniqueId), inclusive = true, callListener = false)
            }
        }
    }
}

private sealed interface PopType {
    data class ById(val id: String) : PopType
    data class ByName(val name: String) : PopType
}
