package com.mirego.kmp.boilerplate.viewmodel.projects

import com.mirego.kmp.boilerplate.extension.eagerlyStateIn
import com.mirego.kmp.boilerplate.extension.prioritiseData
import com.mirego.kmp.boilerplate.localization.KWordTranslation
import com.mirego.kmp.boilerplate.usecase.preview.ProjectsUseCasePreview
import com.mirego.kmp.boilerplate.usecase.projects.ProjectItemViewData
import com.mirego.kmp.boilerplate.usecase.projects.ProjectsUseCase
import com.mirego.kmp.boilerplate.usecase.projects.ProjectsViewData
import com.mirego.kmp.boilerplate.viewmodel.common.EmptyViewModelImpl
import com.mirego.kmp.boilerplate.viewmodel.common.ErrorViewModelImpl
import com.mirego.kmp.boilerplate.viewmodel.common.SharedImageResource
import com.mirego.kmp.boilerplate.viewmodel.navigation.NavigationManager
import com.mirego.kmp.boilerplate.viewmodel.navigation.NavigationRoute
import com.mirego.kmp.boilerplate.viewmodel.projectdetails.ProjectDetailsNavigationData
import com.mirego.pilot.components.PilotRemoteImage
import com.mirego.pilot.viewmodel.viewModelScope
import com.mirego.trikot.datasources.DataState
import com.mirego.trikot.kword.I18N
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.koin.core.annotation.Factory
import org.koin.core.annotation.InjectedParam
import org.koin.core.component.KoinComponent

@Factory
class ProjectsViewModelImpl(
    private val projectsUseCase: ProjectsUseCase,
    private val i18N: I18N,
    @InjectedParam override val navigationManager: NavigationManager
) : ProjectsViewModel(), KoinComponent {
    override val rootContent = projectsUseCase.projects().map { stateData ->
        when (val prioritizedData = stateData.prioritiseData()) {
            is DataState.Data -> when (val data = prioritizedData.value) {
                is ProjectsViewData.Content -> buildData(data)
                is ProjectsViewData.Empty -> buildEmptyData()
            }

            is DataState.Error -> buildError()
            is DataState.Pending -> buildLoading()
        }
    }.eagerlyStateIn(viewModelScope, buildLoading())

    private fun buildData(viewData: ProjectsViewData.Content) = ProjectsRoot.Content(
        sections = listOf(
            buildHeader(),
            buildProjectList(viewData)
        )
    )

    private fun buildEmptyData() = ProjectsRoot.Content(
        sections = listOf(
            buildHeader(),
            buildEmpty()
        )
    )

    private fun buildHeader() = ProjectsContentSection.Header(
        i18N[KWordTranslation.PROJECTS_HEADER_TITLE],
        i18N[KWordTranslation.PROJECTS_HEADER_DESCRIPTION]
    )

    private fun buildProjectList(viewData: ProjectsViewData.Content) = ProjectsContentSection.ProjectsList(
        projects = viewData.items.map { item ->
            item.toItem(isLoading = false)
        }
    )

    private fun ProjectItemViewData.toItem(isLoading: Boolean) = ProjectItem(
        identifier = id,
        title = title,
        subtitle = subtitle,
        description = description,
        image = PilotRemoteImage(
            url = imageUrl,
            placeholder = SharedImageResource.imagePlaceholder
        ),
        tapAction = {
            navigationManager.push(
                NavigationRoute.ProjectDetails(
                    navigationData = ProjectDetailsNavigationData(
                        id = id,
                        backgroundColor = backgroundColor,
                        textColor = textColor
                    ),
                    closeAction = {
                        navigationManager.pop()
                    }
                )
            )
        },
        isLoading = isLoading
    )

    private fun buildEmpty() = ProjectsContentSection.NoProjects(
        emptyViewModel = EmptyViewModelImpl(
            title = i18N[KWordTranslation.GENERIC_EMPTY_CONTENT_TITLE],
            message = i18N[KWordTranslation.PROJECTS_EMPTY_CONTENT_MESSAGE],
            actionButton = null
        )
    )

    private fun buildError() = ProjectsRoot.Error(
        errorViewModel = ErrorViewModelImpl.build(
            i18N = i18N,
            titleKey = KWordTranslation.GENERIC_ERROR_TITLE,
            messageKey = KWordTranslation.GENERIC_ERROR_MESSAGE
        ) {
            viewModelScope.launch {
                projectsUseCase.refreshProjects()
            }
        }
    )

    private fun buildLoading() = ProjectsRoot.Content(
        sections = listOf(
            buildHeader(),
            ProjectsContentSection.ProjectsList(
                projects = ProjectsUseCasePreview.buildPreviewItems().map {
                    it.toItem(isLoading = true)
                }
            )
        )
    )
}
