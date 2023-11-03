package com.mirego.kmp.boilerplate.viewmodel.navigation

import com.mirego.kmp.boilerplate.viewmodel.factory.ViewModelFactory
import com.mirego.kmp.boilerplate.viewmodel.projectdetails.ProjectDetailsNavigationData
import com.mirego.kmp.mirego.trikot.viewmodels.declarative.navigation.VMDNavigationViewModelImpl
import kotlinx.coroutines.CoroutineScope

open class NavigationViewModelImpl(
    onTrackScreenView: () -> Unit,
    private val viewModelFactory: ViewModelFactory,
    coroutineScope: CoroutineScope
) : NavigationViewModel,
    MainNavigationDelegate,
    VMDNavigationViewModelImpl<NavigationRoute>(
        onTrackScreenView = onTrackScreenView,
        coroutineScope = coroutineScope
    ) {

    override fun navigateToProjectDetails(navigationData: ProjectDetailsNavigationData) {
        updateRoute(
            route = NavigationRoute.ProjectDetails(
                viewModel = viewModelFactory.createProjectDetails(
                    navigationData,
                    ::resetRoute,
                    cancelAndCreateCoroutineScope()
                ),
                resetBlock = ::resetRoute
            )
        )
    }
}
