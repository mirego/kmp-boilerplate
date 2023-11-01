package com.mirego.kmp.boilerplate.viewmodel.projectdetails

import com.mirego.kmp.boilerplate.viewmodel.factory.ViewModelFactory
import com.mirego.kmp.boilerplate.viewmodel.navigation.NavigationViewModelImpl
import com.mirego.trikot.viewmodels.declarative.PublishedSubClass
import kotlinx.coroutines.CoroutineScope
import org.koin.core.annotation.Factory

@Factory
@PublishedSubClass(superClass = NavigationViewModelImpl::class)
class ProjectDetailsViewModelImpl(
    viewModelFactory: ViewModelFactory,
    closeAction: () -> Unit,
    coroutineScope: CoroutineScope
) : ProjectDetailsViewModel, BaseProjectDetailsViewModelImpl(
    onTrackScreenView = {},
    viewModelFactory = viewModelFactory,
    coroutineScope = coroutineScope
) {
    
}
