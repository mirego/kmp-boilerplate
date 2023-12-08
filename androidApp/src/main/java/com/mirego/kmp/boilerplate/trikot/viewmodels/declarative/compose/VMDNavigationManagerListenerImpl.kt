package com.mirego.kmp.boilerplate.trikot.viewmodels.declarative.compose

import androidx.navigation.NavController
import com.mirego.kmp.boilerplate.viewmodel.navigation.vmd.VMDNavigationManagerListener
import com.mirego.kmp.boilerplate.viewmodel.navigation.vmd.VMDNavigationRoute

open class VMDNavigationManagerListenerImpl<ROUTE : VMDNavigationRoute>(
    private val navController: NavController
) : VMDNavigationManagerListener<ROUTE>() {

    override fun push(route: ROUTE) {
        navController.navigate(route.navRoute)
    }

    override fun pop() {
        navController.popBackStack()
    }

    override fun popTo(route: ROUTE, inclusive: Boolean) {
        navController.popBackStack(
            route = route.navRoute,
            inclusive = inclusive
        )
    }
}
