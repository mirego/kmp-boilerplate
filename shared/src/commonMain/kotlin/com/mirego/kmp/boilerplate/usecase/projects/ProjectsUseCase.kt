package com.mirego.kmp.boilerplate.usecase.projects

import com.mirego.kmp.boilerplate.utils.StateData
import kotlinx.coroutines.flow.Flow

interface ProjectsUseCase {
    fun projects(): Flow<StateData<ProjectsViewData>>
    suspend fun refreshProjects()
}

sealed interface ProjectsViewData {
    data object Empty : ProjectsViewData

    data class Content(
        val items: List<ProjectItemViewData>
    ) : ProjectsViewData
}

data class ProjectItemViewData(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String
)
