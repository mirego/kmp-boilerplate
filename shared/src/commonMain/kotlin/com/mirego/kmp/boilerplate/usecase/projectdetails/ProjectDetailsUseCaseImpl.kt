package com.mirego.kmp.boilerplate.usecase.projectdetails

import com.mirego.kmp.boilerplate.repository.projectdetails.ProjectDetailsRepository
import com.mirego.kmp.boilerplate.utils.StateData
import com.mirego.trikot.datasources.flow.extensions.mapValue
import com.mirego.trikot.viewmodels.declarative.properties.VMDColor
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

@Factory
class ProjectDetailsUseCaseImpl(
    private val repository: ProjectDetailsRepository
) : ProjectDetailsUseCase {
    companion object {
        private val defaultBackgroundColor = VMDColor(255, 255, 255, 1f)
        private val defaultTextColor = VMDColor(255, 255, 255, 1f)
    }

    override fun projectsDetails(id: String): Flow<StateData<ProjectDetailsViewData>> = repository.projectDetails(id = id)
        .mapValue { entity ->
            ProjectDetailsViewData(
                imageUrl = entity.mainImageUrl.toString(),
                title = entity.client.name,
                subtitle = entity.name,
                projectType = entity.projectType,
                releaseYear = entity.year.toString(),
                backgroundColor = entity.mainColor?.toVMDColor() ?: defaultBackgroundColor,
                textColor = entity.textColor?.toVMDColor() ?: defaultTextColor
            )
        }
}

fun String.toVMDColor(): VMDColor? {
    var hex = this
    hex = hex.replace("#", "")

    if (hex.length != 6) {
        return null
    }

    return VMDColor(
        hex.substring(0, 2).toInt(16),
        hex.substring(2, 4).toInt(16),
        hex.substring(4, 6).toInt(16)
    )
}
