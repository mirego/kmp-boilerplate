package com.mirego.kmp.boilerplate.usecase.projectdetails

import com.mirego.kmp.boilerplate.model.RGBAColor
import com.mirego.kmp.boilerplate.repository.projectdetails.ProjectDetailsRepository
import com.mirego.kmp.boilerplate.utils.StateData
import com.mirego.trikot.datasources.flow.extensions.mapValue
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

@Factory
class ProjectDetailsUseCaseImpl(
    private val repository: ProjectDetailsRepository
) : ProjectDetailsUseCase {
    companion object {
        private val defaultBackgroundColor = RGBAColor(255, 255, 255, 1f)
        private val defaultTextColor = RGBAColor(255, 255, 255, 1f)
    }

    override fun projectsDetails(id: String): Flow<StateData<ProjectDetailsViewData>> = repository.projectDetails(id = id)
        .mapValue { entity ->
            ProjectDetailsViewData(
                imageUrl = entity.mainImageUrl.toString(),
                title = entity.client.name,
                subtitle = entity.name,
                projectType = entity.projectType,
                releaseYear = entity.year.toString(),
                backgroundColor = entity.mainColor?.toRGBAColor() ?: defaultBackgroundColor,
                textColor = entity.textColor?.toRGBAColor() ?: defaultTextColor
            )
        }
}

fun String.toRGBAColor(): RGBAColor? {
    var hex = this
    hex = hex.replace("#", "")

    if (hex.length != 6) {
        return null
    }

    return RGBAColor(
        red = hex.substring(0, 2).toInt(16),
        green = hex.substring(2, 4).toInt(16),
        blue = hex.substring(4, 6).toInt(16)
    )
}
