package com.mirego.kmp.boilerplate.viewmodel.projects

import com.mirego.kmp.boilerplate.viewmodel.common.EmptyViewModel
import com.mirego.kmp.boilerplate.viewmodel.common.ErrorViewModel
import com.mirego.trikot.viewmodels.declarative.Published
import com.mirego.trikot.viewmodels.declarative.components.VMDImageViewModel
import com.mirego.trikot.viewmodels.declarative.components.impl.VMDListViewModelImpl
import com.mirego.trikot.viewmodels.declarative.content.VMDIdentifiableContent
import com.mirego.trikot.viewmodels.declarative.viewmodel.VMDLifecycleViewModel

interface ProjectsViewModel : VMDLifecycleViewModel {
    @Published
    val rootContent: ProjectsRootContent?

}

sealed interface ProjectsRootContent {
    data class Content(
        val items: VMDListViewModelImpl<ProjectItem>
    ) : ProjectsRootContent

    data class Empty(
        val empty: EmptyViewModel
    ) : ProjectsRootContent

    data class Error(
        val error: ErrorViewModel
    ) : ProjectsRootContent
}

data class ProjectItem(
    override val identifier: String,
    val title: String,
    val description: String,
    val image: VMDImageViewModel,
    val isLoading: Boolean
) : VMDIdentifiableContent
