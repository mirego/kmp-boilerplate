package com.mirego.kmp.boilerplate.usecase.preview

import com.mirego.kmp.boilerplate.usecase.projects.ProjectsUseCase
import com.mirego.kmp.boilerplate.usecase.projects.ProjectsViewData
import com.mirego.kmp.boilerplate.utils.StateData
import com.mirego.kmp.boilerplate.utils.stateDataData
import com.mirego.kmp.boilerplate.utils.stateDataError
import com.mirego.kmp.boilerplate.utils.stateDataPending
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlin.time.Duration.Companion.seconds

class ProjectsUseCasePreview(
    previewState: PreviewState
) : ProjectsUseCase {
    private val previewStateFlow = MutableStateFlow(previewState)

    override fun projects(): Flow<StateData<ProjectsViewData>> = previewStateFlow.map {
        when (it) {
            is PreviewState.Data -> {
                when (it) {
                    PreviewState.Data.Content -> stateDataData(
                        ProjectsViewData.Content(
                            emptyList()
                        )
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
