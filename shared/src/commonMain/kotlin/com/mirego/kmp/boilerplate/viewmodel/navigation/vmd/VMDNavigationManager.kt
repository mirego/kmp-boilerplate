package com.mirego.kmp.boilerplate.viewmodel.navigation.vmd

import kotlinx.coroutines.flow.Flow

abstract class VMDNavigationManager<ROUTE : VMDNavigationRoute> {
    abstract val navigationItemList: Flow<List<NavigationItem<ROUTE>>>

    var listener: VMDNavigationManagerListener<ROUTE>? = null

    abstract fun push(route: ROUTE, prioritizeParent: Boolean = false)

    abstract fun pop()
    abstract fun popToId(uniqueId: String, included: Boolean)
    abstract fun popToName(name: String, included: Boolean)
    abstract fun popToRoot()

    abstract fun popped(route: ROUTE)
}

abstract class VMDNavigationManagerListener<ROUTE : VMDNavigationRoute> {
    abstract fun push(route: ROUTE)
    abstract fun pop()
    abstract fun popTo(route: ROUTE, included: Boolean)
}
