package com.mirego.kmp.boilerplate.viewmodel.root

import com.mirego.kmp.boilerplate.viewmodel.navigation.NavigationManager
import com.mirego.kmp.boilerplate.viewmodel.projects.ProjectsViewModel
import org.koin.core.parameter.ParametersHolder
import org.koin.core.parameter.parametersOf

interface RootViewModel {
    val navigationManager: NavigationManager
    val projectsViewModel: ProjectsViewModel

    companion object {
        fun parameters(navigationManager: NavigationManager): ParametersHolder =
            parametersOf(navigationManager)
    }
}
