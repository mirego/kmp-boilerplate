package com.mirego.kmp.boilerplate.usecase.projectdetails

import com.mirego.kmp.boilerplate.repository.projectdetails.ProjectDetailsRepository
import com.mirego.kmp.boilerplate.utils.StateData
import com.mirego.trikot.datasources.flow.extensions.mapValue
import com.mirego.trikot.viewmodels.declarative.properties.VMDColor
import kotlinx.coroutines.flow.Flow

class ProjectDetailsUseCaseImpl(
    private val repository: ProjectDetailsRepository
) : ProjectDetailsUseCase {
    override fun projectsDetails(id: String): Flow<StateData<ProjectDetailsViewData>> =
        repository.projectDetails(id = id)
            .mapValue { entity ->
                ProjectDetailsViewData(
                    entity.mainImageUrl.toString(),
                    entity.introductionText,
                    entity.name,
                    entity.projectType,
                    entity.year.toString(),
                    entity.mainColor?.toVMDColor() ?: VMDColor.None,
                    entity.textColor?.toVMDColor() ?: VMDColor.None
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
