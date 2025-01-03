package com.mirego.kmp.boilerplate.viewmodel.projects

import com.mirego.kmp.boilerplate.viewmodel.common.EmptyViewModel
import com.mirego.kmp.boilerplate.viewmodel.common.ErrorViewModel
import com.mirego.kmp.boilerplate.viewmodel.navigation.NavigationManager
import com.mirego.pilot.components.PilotRemoteImage
import com.mirego.pilot.components.lifecycle.PilotAppearanceLifecycleViewModel
import com.mirego.trikot.viewmodels.declarative.content.VMDIdentifiableContent
import kotlinx.coroutines.flow.Flow
import org.koin.core.parameter.ParametersHolder
import org.koin.core.parameter.parametersOf

abstract class ProjectsViewModel : PilotAppearanceLifecycleViewModel() {
    abstract val rootContent: Flow<ProjectsRoot?>

    companion object {
        fun parameters(navigationManager: NavigationManager): ParametersHolder =
            parametersOf(navigationManager)
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
    data class Header(
        val title: String,
        val description: String,
        val identifier: String = "Header"
    ) : ProjectsContentSection

    data class NoProjects(
        val emptyViewModel: EmptyViewModel,
        val identifier: String = "NoProjects"
    ) : ProjectsContentSection

    data class ProjectsList(
        val projects: List<ProjectItem>,
        val identifier: String = "ProjectsList"
    ) : ProjectsContentSection
}

data class ProjectItem(
    override val identifier: String,
    val title: String,
    val subtitle: String,
    val description: String,
    val image: PilotRemoteImage,
    val tapAction: () -> Unit,
    val isLoading: Boolean
) : VMDIdentifiableContent
