package com.mirego.kmp.boilerplate.viewmodel.navigation

import com.mirego.pilot.navigation.DefaultPilotNavigationManager
import kotlinx.coroutines.CoroutineScope
import org.koin.core.annotation.Factory

@Factory
class NavigationManager(
    coroutineScope: CoroutineScope,
    parentNavigationManager: NavigationManager? = null
) : DefaultPilotNavigationManager<NavigationRoute, NavigationAction>(
    coroutineScope,
    parentNavigationManager
)
