package com.mirego.kmp.boilerplate.viewmodel.navigation.vmd

abstract class VMDNavigationManager<ROUTE : VMDNavigationRoute, ACTION> {
    abstract fun currentRoutes(): List<ROUTE>

    abstract fun <T : ROUTE> findRoute(uniqueId: String): T?

    abstract fun push(route: ROUTE, locally: Boolean = false)
    abstract fun pop()
    abstract fun popToId(uniqueId: String, inclusive: Boolean)
    abstract fun popToName(name: String, inclusive: Boolean)
    abstract fun popToRoot()

    var listener: VMDNavigationManagerListener<ROUTE>? = null
    var actionListener: VMDActionNavigationListener<ACTION>? = null

    abstract fun popped()
    abstract fun poppedFrom(route: ROUTE)

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
