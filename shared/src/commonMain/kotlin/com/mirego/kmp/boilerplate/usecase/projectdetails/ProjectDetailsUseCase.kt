package com.mirego.kmp.boilerplate.usecase.projectdetails

import com.mirego.kmp.boilerplate.model.RGBAColor
import com.mirego.kmp.boilerplate.utils.StateData
import kotlinx.coroutines.flow.Flow

interface ProjectDetailsUseCase {
    fun projectsDetails(id: String): Flow<StateData<ProjectDetailsViewData>>
}

data class ProjectDetailsViewData(
    val imageUrl: String,
    val title: String,
    val subtitle: String,
    val projectType: String,
    val releaseYear: String,
    val backgroundColor: RGBAColor,
    val textColor: RGBAColor
)
