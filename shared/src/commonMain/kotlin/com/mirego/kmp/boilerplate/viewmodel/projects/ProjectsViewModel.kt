package com.mirego.kmp.boilerplate.viewmodel.projects

import com.mirego.kmp.boilerplate.viewmodel.common.EmptyViewModel
import com.mirego.kmp.boilerplate.viewmodel.common.ErrorViewModel
import com.mirego.kmp.boilerplate.viewmodel.navigation.NavigationViewModel
import com.mirego.trikot.viewmodels.declarative.Published
import com.mirego.trikot.viewmodels.declarative.components.VMDImageViewModel
import com.mirego.trikot.viewmodels.declarative.components.VMDListViewModel
import com.mirego.trikot.viewmodels.declarative.content.VMDIdentifiableContent

interface ProjectsViewModel : NavigationViewModel {

    @Published
    val rootContent: ProjectsRoot?
}

sealed interface ProjectsRoot {
    data class Content(
        val sections: VMDListViewModel<ProjectsContentSection>
    ) : ProjectsRoot

    data class Error(
        val errorViewModel: ErrorViewModel
    ) : ProjectsRoot
}

sealed interface ProjectsContentSection : VMDIdentifiableContent {
    data class Header(
        val title: String,
        val description: String
    ) : ProjectsContentSection {
        override val identifier: String = "Header"
    }

    data class NoProjects(
        val emptyViewModel: EmptyViewModel
    ) : ProjectsContentSection {
        override val identifier: String = "NoProjects"
    }

    data class ProjectsList(
        val viewModel: VMDListViewModel<ProjectItem>
    ) : ProjectsContentSection {
        override val identifier: String = "ProjectsList"
    }
}

data class ProjectItem(
    override val identifier: String,
    val title: String,
    val subtitle: String,
    val description: String,
    val image: VMDImageViewModel,
    val tapAction: () -> Unit,
    val isLoading: Boolean
) : VMDIdentifiableContent
