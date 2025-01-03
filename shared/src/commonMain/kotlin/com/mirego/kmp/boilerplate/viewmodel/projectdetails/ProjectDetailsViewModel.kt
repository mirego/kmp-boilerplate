package com.mirego.kmp.boilerplate.viewmodel.projectdetails

import com.mirego.kmp.boilerplate.model.RGBAColor
import com.mirego.kmp.boilerplate.viewmodel.common.ErrorViewModel
import com.mirego.kmp.boilerplate.viewmodel.navigation.NavigationManager
import com.mirego.kmp.boilerplate.viewmodel.navigation.NavigationRoute
import com.mirego.pilot.components.PilotButton
import com.mirego.pilot.components.PilotRemoteImage
import com.mirego.pilot.components.content.PilotLocalImageContent
import com.mirego.pilot.components.lifecycle.PilotAppearanceLifecycleViewModel
import kotlinx.coroutines.flow.Flow
import org.koin.core.parameter.ParametersHolder
import org.koin.core.parameter.parametersOf

abstract class ProjectDetailsViewModel : PilotAppearanceLifecycleViewModel() {
    companion object {
        fun parameters(navigationManager: NavigationManager, route: NavigationRoute.ProjectDetails?): ParametersHolder = parametersOf(navigationManager, route)
    }

    abstract val closeButton: PilotButton<PilotLocalImageContent>

    abstract val backgroundColor: RGBAColor
    abstract val textColor: RGBAColor
    abstract val rootContent: Flow<ProjectDetailsRoot?>

    abstract val navigationManager: NavigationManager
}

sealed interface ProjectDetailsRoot {
    data class Content(
        val image: PilotRemoteImage,
        val title: String,
        val subtitle: String,
        val projectType: Pair<String, String>,
        val releaseYear: Pair<String, String>,
        val backgroundColor: RGBAColor,
        val textColor: RGBAColor,
        val isLoading: Boolean
    ) : ProjectDetailsRoot

    data class Error(
        val errorViewModel: ErrorViewModel
    ) : ProjectDetailsRoot
}
