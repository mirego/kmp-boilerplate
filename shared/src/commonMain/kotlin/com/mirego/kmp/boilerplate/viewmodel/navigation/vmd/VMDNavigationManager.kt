package com.mirego.kmp.boilerplate.viewmodel.navigation.vmd

import kotlinx.coroutines.flow.Flow

abstract class VMDNavigationManager<ROUTE : VMDNavigationRoute, ACTION> {
    abstract val navigationItemList: Flow<List<NavigationItem<ROUTE>>>

    var listener: VMDNavigationManagerListener<ROUTE>? = null

    abstract fun push(route: ROUTE, prioritizeParent: Boolean = false)
    abstract fun pop()
    abstract fun popToId(uniqueId: String, inclusive: Boolean)
    abstract fun popToName(name: String, inclusive: Boolean)
    abstract fun popToRoot()

    abstract fun popped()
    abstract fun poppedFrom(route: ROUTE)

    var actionListener: VMDActionNavigationListener<ACTION>? = null
    abstract fun handleAction(action: ACTION)
}

abstract class VMDNavigationManagerListener<ROUTE : VMDNavigationRoute> {
    abstract fun push(route: ROUTE)
    abstract fun pop()
    abstract fun popTo(route: ROUTE, inclusive: Boolean)
}

abstract class VMDActionNavigationListener<ACTION> {
    abstract fun handleAction(action: ACTION)
}
