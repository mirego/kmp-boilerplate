package com.mirego.kmp.boilerplate.app.di

import com.mirego.kmp.boilerplate.bootstrap.Bootstrapper
import com.mirego.kmp.boilerplate.viewmodel.application.ApplicationViewModel
import com.mirego.kmp.boilerplate.viewmodel.navigation.NavigationManager
import com.mirego.kmp.boilerplate.viewmodel.navigation.NavigationRoute
import com.mirego.kmp.boilerplate.viewmodel.projectdetails.ProjectDetailsViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class IOSKoin(
    bootstrapper: Bootstrapper
) : KoinComponent {
    init {
        bootstrapper.initDependencies()
    }

    fun applicationViewModel(): ApplicationViewModel =
        get()

    fun projectDetailsViewModel(
        navigationManager: NavigationManager,
        route: NavigationRoute.ProjectDetails
    ): ProjectDetailsViewModel =
        get { ProjectDetailsViewModel.parameters(navigationManager, route) }
}
