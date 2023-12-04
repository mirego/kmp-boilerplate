package com.mirego.kmp.boilerplate.viewmodel.navigation.vmd

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

data class NavigationItem<ROUTE : VMDNavigationRoute>(
    val route: ROUTE,
    val coroutineScope: CoroutineScope
)

abstract class VMDNavigationManagerImpl<ROUTE : VMDNavigationRoute>(
    private val coroutineScopeManager: CoroutineScopeManager,
    private val parentNavigationManager: VMDNavigationManager<ROUTE>? = null
) : VMDNavigationManager<ROUTE>() {

    private val internalRouteList: MutableStateFlow<List<NavigationItem<ROUTE>>> =
        MutableStateFlow(emptyList())

    override val navigationItemList: Flow<List<NavigationItem<ROUTE>>>
        get() = internalRouteList

    override fun push(route: ROUTE, prioritizeParent: Boolean) {
        if (prioritizeParent && parentNavigationManager != null) {
            parentNavigationManager.push(route, prioritizeParent = prioritizeParent)
            return
        }

        val newItem = NavigationItem(
            route = route,
            coroutineScope = coroutineScopeManager.createCoroutineScope()
        )
        val newList = internalRouteList.value.toMutableList().apply {
            add(newItem)
        }
        internalRouteList.value = newList
        listener?.push(route)
        println("HUGO DEBUG: pushing ${route.name} count: ${internalRouteList.value.size}")
    }

    override fun pop() {
        internalPop(callListener = true)
    }

    private fun internalPop(callListener: Boolean) {
        val newList = internalRouteList.value.toMutableList().apply {
            removeLastOrNull()?.coroutineScope?.cancel()
        }
        internalRouteList.value = newList
        println("HUGO DEBUG: popping new count: ${internalRouteList.value.size}")

        if (callListener) {
            listener?.pop()
        }
    }

    override fun popToId(uniqueId: String, included: Boolean) {
        internalPopTo(popType = PopType.ById(uniqueId), included = included, callListener = true)
    }

    override fun popToName(name: String, included: Boolean) {
        internalPopTo(popType = PopType.ByName(name), included = included, callListener = true)
    }

    override fun popToRoot() {
        val firstItem = internalRouteList.value.firstOrNull() ?: return
        internalPopTo(popType = PopType.ById(firstItem.route.uniqueId), included = true, callListener = true)
    }

    private fun internalPopTo(popType: PopType, included: Boolean, callListener: Boolean) {
        println("HUGO DEBUG: internalPopTo start count: ${internalRouteList.value.size}")
        val navigationItem = internalRouteList.value
            .lastOrNull { item ->
                when (popType) {
                    is PopType.ById -> item.route.uniqueId == popType.id
                    is PopType.ByName -> item.route.name == popType.name
                }
            } ?: return
        val index = internalRouteList.value.indexOf(navigationItem)

        if (index != -1 && internalRouteList.value.isNotEmpty()) {
            val effectiveIndex = index + if (included) 0 else 1
            (effectiveIndex until internalRouteList.value.size).forEach {
                val item = internalRouteList.value[it]
                item.coroutineScope.cancel()
            }

            internalRouteList.value = internalRouteList.value.slice(0..< effectiveIndex).toMutableList()
            println("HUGO DEBUG: internalPopTo final count: ${internalRouteList.value.size}")
            if (callListener) {
                listener?.popTo(navigationItem.route, included = included)
            }
        }
    }

    override fun popped(route: ROUTE) {
        val routeIndex = internalRouteList.value.indexOfFirst {
            it.route.uniqueId == route.uniqueId
        }
        if (routeIndex != -1) {
            internalPopTo(popType = PopType.ById(route.uniqueId), included = true, callListener = false)
        }
    }

    protected fun getCoroutineScope(route: ROUTE): CoroutineScope = internalRouteList.value
        .lastOrNull { it.route.uniqueId == route.uniqueId }
        ?.coroutineScope ?: coroutineScopeManager.createCoroutineScope()
}

private sealed interface PopType {
    data class ById(val id: String): PopType
    data class ByName(val name: String): PopType
}