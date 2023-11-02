package com.mirego.kmp.boilerplate.usecase.projects

import com.mirego.kmp.boilerplate.repository.projects.ProjectsRepository
import com.mirego.kmp.boilerplate.usecase.projectdetails.toVMDColor
import com.mirego.kmp.boilerplate.utils.StateData
import com.mirego.trikot.datasources.flow.extensions.mapValue
import com.mirego.trikot.viewmodels.declarative.properties.VMDColor
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
                            title = project.client.name,
                            subtitle = project.name,
                            description = project.projectType,
                            imageUrl = project.listImageUrl.toString(),
                            backgroundColor = project.mainColor?.toVMDColor() ?: VMDColor.None,
                            textColor = project.textColor?.toVMDColor() ?: VMDColor.None
                        )
                    }
                )
            }
        }

    override suspend fun refreshProjects() = projectsRepository.refreshProjects()
}
