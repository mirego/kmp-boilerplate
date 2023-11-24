package com.mirego.kmp.boilerplate.viewmodel.factory

import com.mirego.kmp.boilerplate.viewmodel.projectdetails.ProjectDetailsNavigationData
import com.mirego.kmp.boilerplate.viewmodel.projectdetails.ProjectDetailsViewModel
import com.mirego.kmp.boilerplate.viewmodel.projects.ProjectsViewModel
import com.mirego.kmp.boilerplate.viewmodel.root.RootViewModel
import kotlinx.coroutines.CoroutineScope

interface ViewModelFactory {
    fun createRoot(coroutineScope: CoroutineScope): RootViewModel
    fun createProjects(coroutineScope: CoroutineScope): ProjectsViewModel
    fun createProjectDetails(navigationData: ProjectDetailsNavigationData, closeAction: () -> Unit, coroutineScope: CoroutineScope): ProjectDetailsViewModel
}
