package com.mirego.kmp.boilerplate.viewmodel.projectdetails

import com.mirego.kmp.boilerplate.viewmodel.common.ErrorViewModel
import com.mirego.kmp.boilerplate.viewmodel.navigation.NavigationViewModel
import com.mirego.trikot.viewmodels.declarative.Published

interface ProjectDetailsViewModel : NavigationViewModel {
    @Published
    val rootContent: ProjectDetailsRoot?
}

sealed interface ProjectDetailsRoot {
    data class Content(
        val title: String
    ) : ProjectDetailsRoot

    data class Error(
        val errorViewModel: ErrorViewModel
    ) : ProjectDetailsRoot
}
