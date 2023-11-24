package com.mirego.kmp.boilerplate.viewmodel.root

import com.mirego.kmp.boilerplate.viewmodel.projects.ProjectsViewModel
import com.mirego.trikot.viewmodels.declarative.viewmodel.VMDViewModel

interface RootViewModel : VMDViewModel {
    val projectsViewModel: ProjectsViewModel
}
