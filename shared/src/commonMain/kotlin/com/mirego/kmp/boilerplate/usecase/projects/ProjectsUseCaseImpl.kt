package com.mirego.kmp.boilerplate.usecase.projects

import com.mirego.kmp.boilerplate.repository.projects.ProjectsRepository
import com.mirego.kmp.boilerplate.utils.StateData
import com.mirego.trikot.datasources.flow.extensions.mapValue
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

@Factory
class ProjectsUseCaseImpl(
    private val projectsRepository: ProjectsRepository
) : ProjectsUseCase {
    override fun projects(): Flow<StateData<ProjectsViewData>> = projectsRepository
        .projects()
        .mapValue {
            if (it.isEmpty()) {
                ProjectsViewData.Empty
            } else {
                ProjectsViewData.Content(
                    it.map { project ->
                        ProjectItemViewData(
                            id = project.pageSlug,
                            title = project.name,
                            description = project.introductionText,
                            imageUrl = project.listImageUrl.toString()
                        )
                    }
                )
            }
        }

    override suspend fun refreshProjects() = projectsRepository.refreshProjects()
}
