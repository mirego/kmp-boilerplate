package com.mirego.kmp.boilerplate.viewmodel.projectdetails

import com.mirego.kmp.boilerplate.viewmodel.common.ErrorViewModel
import com.mirego.kmp.boilerplate.viewmodel.navigation.NavigationViewModel
import com.mirego.pilot.components.PilotButton
import com.mirego.pilot.components.PilotRemoteImage
import com.mirego.pilot.components.content.PilotLocalImageContent
import com.mirego.trikot.viewmodels.declarative.Published
import com.mirego.trikot.viewmodels.declarative.properties.VMDColor

interface ProjectDetailsViewModel : NavigationViewModel {
    val closeButton: PilotButton<PilotLocalImageContent>

    val backgroundColor: VMDColor
    val textColor: VMDColor

    @Published
    val rootContent: ProjectDetailsRoot?
}

sealed interface ProjectDetailsRoot {
    data class Content(
        val image: PilotRemoteImage,
        val title: String,
        val subtitle: String,
        val projectType: Pair<String, String>,
        val releaseYear: Pair<String, String>,
        val backgroundColor: VMDColor,
        val textColor: VMDColor,
        val isLoading: Boolean
    ) : ProjectDetailsRoot

    data class Error(
        val errorViewModel: ErrorViewModel
    ) : ProjectDetailsRoot
}
