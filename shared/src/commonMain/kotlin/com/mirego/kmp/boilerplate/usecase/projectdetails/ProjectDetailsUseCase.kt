package com.mirego.kmp.boilerplate.usecase.projectdetails

import com.mirego.kmp.boilerplate.utils.StateData
import com.mirego.trikot.viewmodels.declarative.properties.VMDColor
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
    val backgroundColor: VMDColor,
    val textColor: VMDColor
)
