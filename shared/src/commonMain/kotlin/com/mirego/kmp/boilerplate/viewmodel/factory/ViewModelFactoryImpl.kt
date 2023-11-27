package com.mirego.kmp.boilerplate.viewmodel.factory

import com.mirego.kmp.boilerplate.viewmodel.projectdetails.ProjectDetailsNavigationData
import com.mirego.kmp.boilerplate.viewmodel.projectdetails.ProjectDetailsViewModel
import com.mirego.kmp.boilerplate.viewmodel.projects.ProjectsViewModel
import com.mirego.kmp.boilerplate.viewmodel.root.RootViewModel
import kotlinx.coroutines.CoroutineScope
import org.koin.core.annotation.Single
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf

@Single
class ViewModelFactoryImpl : ViewModelFactory, KoinComponent {
    override fun createRoot(coroutineScope: CoroutineScope): RootViewModel = get {
        parametersOf(coroutineScope)
    }

    override fun createProjects(coroutineScope: CoroutineScope): ProjectsViewModel = get {
        parametersOf(coroutineScope)
    }

    override fun createProjectDetails(navigationData: ProjectDetailsNavigationData, closeAction: () -> Unit, coroutineScope: CoroutineScope): ProjectDetailsViewModel = get {
        parametersOf(navigationData, closeAction, coroutineScope)
    }
}
