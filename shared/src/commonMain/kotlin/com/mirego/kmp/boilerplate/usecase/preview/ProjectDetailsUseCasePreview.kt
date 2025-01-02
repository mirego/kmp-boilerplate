package com.mirego.kmp.boilerplate.usecase.preview

import com.mirego.kmp.boilerplate.model.RGBAColor
import com.mirego.kmp.boilerplate.usecase.projectdetails.ProjectDetailsUseCase
import com.mirego.kmp.boilerplate.usecase.projectdetails.ProjectDetailsViewData
import com.mirego.kmp.boilerplate.usecase.projectdetails.toRGBAColor
import com.mirego.kmp.boilerplate.utils.StateData
import com.mirego.kmp.boilerplate.utils.stateDataData
import com.mirego.kmp.boilerplate.utils.stateDataError
import com.mirego.kmp.boilerplate.utils.stateDataPending
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
            "FFFFFF".toRGBAColor() ?: RGBAColor.None,
            "000000".toRGBAColor() ?: RGBAColor.None
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
