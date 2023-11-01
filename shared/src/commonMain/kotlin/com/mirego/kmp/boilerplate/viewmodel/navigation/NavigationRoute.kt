package com.mirego.kmp.boilerplate.viewmodel.navigation

import com.mirego.kmp.boilerplate.viewmodel.projectdetails.ProjectDetailsViewModel
import com.mirego.kmp.mirego.trikot.viewmodels.declarative.navigation.VMDNavigationRoute

sealed interface NavigationRoute : VMDNavigationRoute {
    data class ProjectDetails(
        override val viewModel: ProjectDetailsViewModel,
        override val resetBlock: () -> Unit
    ) : NavigationRoute {
        companion object {
            const val NAME = "ProjectDetails"
        }

        override val name: String = NAME
    }
}
