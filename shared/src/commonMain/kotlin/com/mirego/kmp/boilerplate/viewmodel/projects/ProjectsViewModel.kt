package com.mirego.kmp.boilerplate.viewmodel.projects

import com.mirego.kmp.boilerplate.viewmodel.common.EmptyViewModel
import com.mirego.kmp.boilerplate.viewmodel.common.ErrorViewModel
import com.mirego.kmp.boilerplate.viewmodel.navigation.NavigationManager
import com.mirego.pilot.components.PilotRemoteImage
import com.mirego.pilot.components.lifecycle.PilotAppearanceLifecycleViewModel
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.parameter.ParametersHolder
import org.koin.core.parameter.parametersOf

abstract class ProjectsViewModel : PilotAppearanceLifecycleViewModel() {
    abstract val rootContent: StateFlow<ProjectsRoot>
    abstract val navigationManager: NavigationManager

    companion object {
        fun parameters(navigationManager: NavigationManager): ParametersHolder = parametersOf(navigationManager)
    }
}

sealed interface ProjectsRoot {
    data class Content(
        val sections: List<ProjectsContentSection>
    ) : ProjectsRoot

    data class Error(
        val errorViewModel: ErrorViewModel
    ) : ProjectsRoot
}

sealed interface ProjectsContentSection {
    val identifier: String

    data class Header(
        val title: String,
        val description: String
    ) : ProjectsContentSection {
        override val identifier = "Header"
    }

    data class NoProjects(
        val emptyViewModel: EmptyViewModel
    ) : ProjectsContentSection {
        override val identifier = "NoProjects"
    }

    data class ProjectsList(
        val projects: List<ProjectItem>
    ) : ProjectsContentSection {
        override val identifier = "ProjectsList"
    }
}

data class ProjectItem(
    val identifier: String,
    val title: String,
    val subtitle: String,
    val description: String,
    val image: PilotRemoteImage,
    val tapAction: () -> Unit,
    val isLoading: Boolean
)
