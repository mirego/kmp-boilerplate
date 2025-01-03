package com.mirego.kmp.boilerplate.viewmodel.root

import com.mirego.kmp.boilerplate.viewmodel.navigation.NavigationManager
import com.mirego.kmp.boilerplate.viewmodel.projects.ProjectsViewModel
import com.mirego.pilot.viewmodel.PilotViewModel
import kotlinx.coroutines.CoroutineScope
import org.koin.core.parameter.ParametersHolder
import org.koin.core.parameter.parametersOf

abstract class RootViewModel : PilotViewModel() {
    abstract val projectsViewModel: ProjectsViewModel

    companion object {
        fun parameters(navigationManager: NavigationManager, viewModelScope: CoroutineScope): ParametersHolder =
            parametersOf(navigationManager, viewModelScope)
    }
}
