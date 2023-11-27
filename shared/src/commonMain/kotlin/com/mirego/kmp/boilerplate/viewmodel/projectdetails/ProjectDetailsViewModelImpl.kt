package com.mirego.kmp.boilerplate.viewmodel.projectdetails

import com.mirego.kmp.boilerplate.analytics.Analytics
import com.mirego.kmp.boilerplate.analytics.ScreenName
import com.mirego.kmp.boilerplate.localization.KWordTranslation
import com.mirego.kmp.boilerplate.usecase.preview.ProjectDetailsUseCasePreview
import com.mirego.kmp.boilerplate.usecase.projectdetails.ProjectDetailsUseCase
import com.mirego.kmp.boilerplate.usecase.projectdetails.ProjectDetailsViewData
import com.mirego.kmp.boilerplate.viewmodel.common.ErrorViewModelImpl
import com.mirego.kmp.boilerplate.viewmodel.common.SharedImageResource
import com.mirego.kmp.boilerplate.viewmodel.factory.ViewModelFactory
import com.mirego.kmp.boilerplate.viewmodel.navigation.NavigationViewModelImpl
import com.mirego.trikot.datasources.DataState
import com.mirego.trikot.kword.I18N
import com.mirego.trikot.viewmodels.declarative.PublishedSubClass
import com.mirego.trikot.viewmodels.declarative.content.VMDTextPairContent
import com.mirego.trikot.viewmodels.declarative.properties.VMDColor
import com.mirego.trikot.viewmodels.declarative.viewmodel.buttonWithImage
import com.mirego.trikot.viewmodels.declarative.viewmodel.remoteImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory

@Factory
@PublishedSubClass(superClass = NavigationViewModelImpl::class)
class ProjectDetailsViewModelImpl(
    private val navigationData: ProjectDetailsNavigationData,
    projectDetailsUseCase: ProjectDetailsUseCase,
    private val i18N: I18N,
    viewModelFactory: ViewModelFactory,
    closeAction: () -> Unit,
    coroutineScope: CoroutineScope
) : ProjectDetailsViewModel, BaseProjectDetailsViewModelImpl(
    onTrackScreenView = {
        Analytics.trackScreenView(ScreenName.project_details)
    },
    viewModelFactory = viewModelFactory,
    coroutineScope = coroutineScope
) {
    override val backgroundColor: VMDColor = navigationData.backgroundColor
    override val textColor: VMDColor = navigationData.textColor

    init {
        bindRootContent(
            projectDetailsUseCase.projectsDetails(navigationData.id).map { stateData ->
                when (stateData) {
                    is DataState.Data -> buildContent(stateData.value, false)
                    is DataState.Pending -> buildLoading()
                    is DataState.Error -> buildError()
                }
            }
        )
    }

    private fun buildContent(viewData: ProjectDetailsViewData, isLoading: Boolean) = ProjectDetailsRoot.Content(
        image = remoteImage(
            imageUrl = viewData.imageUrl,
            placeholderImageResource = SharedImageResource.imagePlaceholder
        ),
        title = viewData.title,
        subtitle = viewData.subtitle,
        projectType = VMDTextPairContent(
            i18N[KWordTranslation.PROJECT_DETAILS_PROJECT_TYPE],
            viewData.projectType
        ),
        releaseYear = VMDTextPairContent(
            i18N[KWordTranslation.PROJECT_DETAILS_RELEASE_YEAR],
            viewData.releaseYear
        ),
        backgroundColor = viewData.backgroundColor,
        textColor = viewData.textColor,
        isLoading
    )

    private fun buildLoading() = buildContent(
        viewData = ProjectDetailsUseCasePreview.buildPreviewViewData()
            .copy(backgroundColor = navigationData.backgroundColor, textColor = navigationData.textColor),
        isLoading = true
    )

    private fun buildError() = ProjectDetailsRoot.Error(
        errorViewModel = ErrorViewModelImpl.build(
            i18N = i18N,
            titleKey = KWordTranslation.GENERIC_ERROR_TITLE,
            messageKey = KWordTranslation.GENERIC_ERROR_MESSAGE,
            coroutineScope = coroutineScope,
            retryAction = {}
        )
    )

    override val closeButton = buttonWithImage(image = SharedImageResource.closeIcon) {
        setAction {
            closeAction()
        }
    }
}
