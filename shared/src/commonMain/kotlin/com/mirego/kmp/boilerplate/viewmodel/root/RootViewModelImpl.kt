package com.mirego.kmp.boilerplate.viewmodel.root

import com.mirego.kmp.boilerplate.viewmodel.navigation.NavigationManager
import com.mirego.kmp.boilerplate.viewmodel.projects.ProjectsViewModel
import org.koin.core.annotation.Factory
import org.koin.core.annotation.InjectedParam
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@Factory
internal class RootViewModelImpl(
    @InjectedParam override val navigationManager: NavigationManager
) : RootViewModel, KoinComponent {
    override val projectsViewModel: ProjectsViewModel by inject { ProjectsViewModel.parameters(navigationManager) }
}
