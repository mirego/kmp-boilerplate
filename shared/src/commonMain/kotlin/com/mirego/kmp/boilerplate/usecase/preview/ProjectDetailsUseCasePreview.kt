package com.mirego.kmp.boilerplate.usecase.preview

import com.mirego.kmp.boilerplate.usecase.projectdetails.ProjectDetailsUseCase
import com.mirego.kmp.boilerplate.usecase.projectdetails.ProjectDetailsViewData
import com.mirego.kmp.boilerplate.usecase.projectdetails.toVMDColor
import com.mirego.kmp.boilerplate.utils.StateData
import com.mirego.kmp.boilerplate.utils.stateDataData
import com.mirego.kmp.boilerplate.utils.stateDataError
import com.mirego.kmp.boilerplate.utils.stateDataPending
import com.mirego.trikot.viewmodels.declarative.properties.VMDColor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ProjectDetailsUseCasePreview(private val previewState: PreviewState) : ProjectDetailsUseCase {
    companion object {
        fun buildPreviewViewData() = ProjectDetailsViewData(
            "",
            "Mirego",
            "We make cool stuff",
            "KMP mobile apps",
            "2023",
            "FFFFFF".toVMDColor() ?: VMDColor.None,
            "000000".toVMDColor() ?: VMDColor.None
        )
    }

    override fun projectsDetails(id: String): Flow<StateData<ProjectDetailsViewData>> = flowOf(
        when (previewState) {
            is PreviewState.Data -> stateDataData(buildPreviewViewData())
            PreviewState.Loading -> stateDataPending()
            PreviewState.Error -> stateDataError(Throwable())
        }
    )
}
