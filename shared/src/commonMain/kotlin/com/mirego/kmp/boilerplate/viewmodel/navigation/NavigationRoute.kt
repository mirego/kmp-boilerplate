package com.mirego.kmp.boilerplate.viewmodel.navigation

import com.mirego.kmp.boilerplate.viewmodel.projectdetails.ProjectDetailsNavigationData
import com.mirego.pilot.navigation.EnumPilotNavigationRoute

enum class NavigationRouteName {
    PROJECT_DETAILS
}

sealed class NavigationRoute(routeName: NavigationRouteName) : EnumPilotNavigationRoute(routeName) {
    data class ProjectDetails(
        val navigationData: ProjectDetailsNavigationData
    ) : NavigationRoute(NavigationRouteName.PROJECT_DETAILS)
}
