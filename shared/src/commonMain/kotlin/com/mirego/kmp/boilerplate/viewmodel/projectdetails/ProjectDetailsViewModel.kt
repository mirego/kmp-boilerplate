package com.mirego.kmp.boilerplate.viewmodel.projectdetails

import com.mirego.kmp.boilerplate.viewmodel.common.ErrorViewModel
import com.mirego.kmp.boilerplate.viewmodel.navigation.NavigationViewModel
import com.mirego.trikot.viewmodels.declarative.Published
import com.mirego.trikot.viewmodels.declarative.components.VMDButtonViewModel
import com.mirego.trikot.viewmodels.declarative.content.VMDImageContent
import com.mirego.trikot.viewmodels.declarative.content.VMDTextPairContent
import com.mirego.trikot.viewmodels.declarative.properties.VMDColor

interface ProjectDetailsViewModel : NavigationViewModel {
    val closeButton: VMDButtonViewModel<VMDImageContent>

    @Published
    val rootContent: ProjectDetailsRoot?
}

sealed interface ProjectDetailsRoot {
    data class Content(
        val imageUrl: String,
        val title: String,
        val subtitle: String,
        val projectType: VMDTextPairContent,
        val releaseYear: VMDTextPairContent,
        val backgroundColor: VMDColor,
        val textColor: VMDColor,
        val isLoading: Boolean
    ) : ProjectDetailsRoot

    data class Error(
        val errorViewModel: ErrorViewModel
    ) : ProjectDetailsRoot
}
