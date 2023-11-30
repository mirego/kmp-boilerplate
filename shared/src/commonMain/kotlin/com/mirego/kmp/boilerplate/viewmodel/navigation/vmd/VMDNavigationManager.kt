package com.mirego.kmp.boilerplate.viewmodel.navigation.vmd

import kotlinx.coroutines.flow.Flow

abstract class VMDNavigationManager<ROUTE : VMDNavigationRoute, RESULT : VMDNavigationResult> {
    abstract val navigationItemList: Flow<List<NavigationItem<ROUTE, RESULT>>>

    var listener: VMDNavigationManagerListener<ROUTE>? = null

    abstract fun present(route: ROUTE, closeAction: (RESULT?) -> Unit = {})
    abstract fun push(route: ROUTE, closeAction: (RESULT?) -> Unit = {})
    abstract fun pop(result: RESULT? = null)
    abstract fun popTo(route: ROUTE, included: Boolean)

    abstract fun popped(route: ROUTE)
}

abstract class VMDNavigationManagerListener<ROUTE : VMDNavigationRoute> {
    abstract fun push(route: ROUTE)
    abstract fun pop()
    abstract fun popTo(route: ROUTE)
}
