package com.mirego.kmp.boilerplate.viewmodel.projects

import com.mirego.kmp.boilerplate.analytics.Analytics
import com.mirego.kmp.boilerplate.analytics.ScreenName
import com.mirego.kmp.boilerplate.extension.prioritiseData
import com.mirego.kmp.boilerplate.localization.KWordTranslation
import com.mirego.kmp.boilerplate.usecase.preview.ProjectsUseCasePreview
import com.mirego.kmp.boilerplate.usecase.projects.ProjectItemViewData
import com.mirego.kmp.boilerplate.usecase.projects.ProjectsUseCase
import com.mirego.kmp.boilerplate.usecase.projects.ProjectsViewData
import com.mirego.kmp.boilerplate.viewmodel.common.EmptyViewModelImpl
import com.mirego.kmp.boilerplate.viewmodel.common.ErrorViewModelImpl
import com.mirego.kmp.boilerplate.viewmodel.common.SharedImageResource
import com.mirego.kmp.boilerplate.viewmodel.factory.ViewModelFactory
import com.mirego.kmp.boilerplate.viewmodel.navigation.NavigationViewModelImpl
import com.mirego.kmp.boilerplate.viewmodel.projectdetails.ProjectDetailsNavigationData
import com.mirego.trikot.datasources.DataState
import com.mirego.trikot.kword.I18N
import com.mirego.trikot.viewmodels.declarative.PublishedSubClass
import com.mirego.trikot.viewmodels.declarative.viewmodel.list
import com.mirego.trikot.viewmodels.declarative.viewmodel.remoteImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.koin.core.annotation.Factory

@Factory
@PublishedSubClass(superClass = NavigationViewModelImpl::class)
class ProjectsViewModelImpl(
    private val projectsUseCase: ProjectsUseCase,
    private val i18N: I18N,
    viewModelFactory: ViewModelFactory,
    coroutineScope: CoroutineScope
) : ProjectsViewModel, BaseProjectsViewModelImpl(
    onTrackScreenView = {
        Analytics.trackScreenView(ScreenName.projects)
    },
    viewModelFactory = viewModelFactory,
    coroutineScope = coroutineScope
) {

    init {
        bindRootContent(
            projectsUseCase.projects().map { stateData ->
                when (val prioritizedData = stateData.prioritiseData()) {
                    is DataState.Data -> when (val data = prioritizedData.value) {
                        is ProjectsViewData.Content -> buildData(data)
                        is ProjectsViewData.Empty -> buildEmptyData()
                    }

                    is DataState.Error -> buildError()
                    is DataState.Pending -> buildLoading()
                }
            }
        )
    }

    private fun buildData(viewData: ProjectsViewData.Content) = ProjectsRoot.Content(
        sections = list {
            elements = listOf(
                buildHeader(),
                buildProjectList(viewData)
            )
        }
    )

    private fun buildEmptyData() = ProjectsRoot.Content(
        sections = list {
            elements = listOf(
                buildHeader(),
                buildEmpty()
            )
        }
    )

    private fun buildHeader() = ProjectsContentSection.Header(
        i18N[KWordTranslation.PROJECTS_HEADER_TITLE],
        i18N[KWordTranslation.PROJECTS_HEADER_DESCRIPTION]
    )

    private fun buildProjectList(viewData: ProjectsViewData.Content) = ProjectsContentSection.ProjectsList(
        viewModel = list(
            elements = viewData.items.map { item ->
                item.toItem(isLoading = false)
            }
        )
    )

    private fun ProjectItemViewData.toItem(isLoading: Boolean) = ProjectItem(
        identifier = id,
        title = title,
        subtitle = subtitle,
        description = description,
        image = remoteImage(
            imageUrl = imageUrl,
            placeholderImageResource = SharedImageResource.imagePlaceholder
        ),
        tapAction = {
            Analytics.trackViewProject(projectId = id)
            navigateToProjectDetails(
                ProjectDetailsNavigationData(
                    id = id,
                    backgroundColor = backgroundColor,
                    textColor = textColor
                )
            )
        },
        isLoading = isLoading
    )

    private fun buildEmpty() = ProjectsContentSection.NoProjects(
        emptyViewModel = EmptyViewModelImpl(
            title = i18N[KWordTranslation.GENERIC_EMPTY_CONTENT_TITLE],
            message = i18N[KWordTranslation.PROJECTS_EMPTY_CONTENT_MESSAGE],
            actionButton = null,
            coroutineScope = coroutineScope
        )
    )

    private fun buildError() = ProjectsRoot.Error(
        errorViewModel = ErrorViewModelImpl.build(
            i18N = i18N,
            titleKey = KWordTranslation.GENERIC_ERROR_TITLE,
            messageKey = KWordTranslation.GENERIC_ERROR_MESSAGE,
            coroutineScope = coroutineScope

        ) {
            coroutineScope.launch {
                projectsUseCase.refreshProjects()
            }
        }
    )

    private fun buildLoading() = ProjectsRoot.Content(
        sections = list {
            elements = listOf(
                buildHeader(),
                ProjectsContentSection.ProjectsList(
                    viewModel = list(
                        elements = ProjectsUseCasePreview.buildPreviewItems().map {
                            it.toItem(isLoading = true)
                        }
                    )
                )
            )
        }
    )
}
