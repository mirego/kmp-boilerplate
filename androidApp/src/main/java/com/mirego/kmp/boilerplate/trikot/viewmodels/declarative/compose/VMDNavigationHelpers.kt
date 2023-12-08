package com.mirego.kmp.boilerplate.trikot.viewmodels.declarative.compose

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.mirego.kmp.boilerplate.viewmodel.navigation.vmd.VMDNavigationManager
import com.mirego.kmp.boilerplate.viewmodel.navigation.vmd.VMDNavigationRoute

private const val uniqueIdParam = "uniqueId"

val VMDNavigationRoute.navRoute: String
    get() = "${name}/${uniqueId}"

fun vmdNavRoute(name: String) = "$name/{$uniqueIdParam}"
val vmdNavArguments: List<NamedNavArgument> = listOf(navArgument(uniqueIdParam) { type = NavType.StringType })

fun <ROUTE : VMDNavigationRoute, ACTION, T : ROUTE> VMDNavigationManager<ROUTE, ACTION>.findRoute(
    backStackEntry: NavBackStackEntry
): T {
    val uniqueId = backStackEntry.arguments?.getString(uniqueIdParam)
        ?: throw IllegalStateException("Unique ID not found in backStackEntry $backStackEntry")
    return (findRoute(uniqueId) as? T)
        ?: throw IllegalStateException("Cannot find route with id $uniqueId")
}
