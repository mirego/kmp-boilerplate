package com.mirego.kmp.boilerplate.viewmodel.root

import com.mirego.kmp.boilerplate.viewmodel.factory.ViewModelFactory
import com.mirego.kmp.boilerplate.viewmodel.projects.ProjectsViewModel
import com.mirego.trikot.kword.I18N
import com.mirego.trikot.viewmodels.declarative.viewmodel.VMDViewModelImpl
import kotlinx.coroutines.CoroutineScope
import org.koin.core.annotation.Factory

@Factory
class RootViewModelImpl(
    i18N: I18N,
    viewModelFactory: ViewModelFactory,
    coroutineScope: CoroutineScope
) : RootViewModel, VMDViewModelImpl(coroutineScope) {
    override val projectsViewModel: ProjectsViewModel =
        viewModelFactory.createProjects(coroutineScope)
}
