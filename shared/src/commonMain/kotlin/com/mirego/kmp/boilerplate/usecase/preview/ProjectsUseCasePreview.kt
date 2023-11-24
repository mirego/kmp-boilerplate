package com.mirego.kmp.boilerplate.usecase.preview

import com.mirego.kmp.boilerplate.usecase.projectdetails.toVMDColor
import com.mirego.kmp.boilerplate.usecase.projects.ProjectItemViewData
import com.mirego.kmp.boilerplate.usecase.projects.ProjectsUseCase
import com.mirego.kmp.boilerplate.usecase.projects.ProjectsViewData
import com.mirego.kmp.boilerplate.utils.StateData
import com.mirego.kmp.boilerplate.utils.stateDataData
import com.mirego.kmp.boilerplate.utils.stateDataError
import com.mirego.kmp.boilerplate.utils.stateDataPending
import com.mirego.trikot.viewmodels.declarative.properties.VMDColor
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class ProjectsUseCasePreview(
    previewState: PreviewState
) : ProjectsUseCase {
    companion object {
        fun buildPreviewItems() = (0 until 5).map {
            ProjectItemViewData(
                id = it.toString(),
                title = "Project #$it",
                subtitle = "A small project description #$it",
                description = "iOS & Android applications",
                imageUrl = "",
                backgroundColor = "000000".toVMDColor() ?: VMDColor.None,
                textColor = "FFFFFF".toVMDColor() ?: VMDColor.None
            )
        }
    }

    private val previewStateFlow = MutableStateFlow(previewState)

    override fun projects(): Flow<StateData<ProjectsViewData>> = previewStateFlow.map {
        when (it) {
            is PreviewState.Data -> {
                when (it) {
                    PreviewState.Data.Content -> stateDataData(
                        ProjectsViewData.Content(buildPreviewItems())
                    )

                    PreviewState.Data.Empty -> stateDataData(
                        ProjectsViewData.Empty
                    )
                }
            }

            PreviewState.Loading -> stateDataPending()
            PreviewState.Error -> stateDataError(Throwable())
        }
    }

    override suspend fun refreshProjects() {
        previewStateFlow.value = PreviewState.Loading
        delay(2.seconds)
        previewStateFlow.value = PreviewState.Data.Content
    }
}
