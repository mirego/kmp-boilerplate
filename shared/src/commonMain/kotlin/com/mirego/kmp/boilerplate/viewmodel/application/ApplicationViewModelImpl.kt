package com.mirego.kmp.boilerplate.viewmodel.application

import com.mirego.kmp.boilerplate.viewmodel.navigation.NavigationManager
import com.mirego.kmp.boilerplate.viewmodel.root.RootViewModel
import com.mirego.pilot.viewmodel.viewModelScope
import org.koin.core.annotation.Factory
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@Factory
internal class ApplicationViewModelImpl : ApplicationViewModel(), KoinComponent {
    override val navigationManager = NavigationManager(viewModelScope)
    override val rootViewModel: RootViewModel by inject { RootViewModel.parameters(navigationManager, viewModelScope = viewModelScope) }
}
