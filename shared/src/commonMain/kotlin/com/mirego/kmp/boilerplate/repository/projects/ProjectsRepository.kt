package com.mirego.kmp.boilerplate.repository.projects

import com.mirego.kmp.boilerplate.ProjectsQuery
import com.mirego.kmp.boilerplate.utils.StateData
import kotlinx.coroutines.flow.Flow

interface ProjectsRepository {
    fun projects(): Flow<StateData<List<ProjectsQuery.Data.PagePage.ProjectsListBlock.Projects.Entry>>>
    suspend fun refreshProjects()
}
