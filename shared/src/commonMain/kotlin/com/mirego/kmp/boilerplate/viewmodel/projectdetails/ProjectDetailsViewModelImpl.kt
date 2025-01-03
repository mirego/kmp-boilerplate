package com.mirego.kmp.boilerplate.viewmodel.projectdetails

import com.mirego.kmp.boilerplate.analytics.Analytics
import com.mirego.kmp.boilerplate.analytics.ScreenName
import com.mirego.kmp.boilerplate.extension.eagerlyStateIn
import com.mirego.kmp.boilerplate.extension.stateFlowOf
import com.mirego.kmp.boilerplate.localization.KWordTranslation
import com.mirego.kmp.boilerplate.model.RGBAColor
import com.mirego.kmp.boilerplate.usecase.preview.ProjectDetailsUseCasePreview
import com.mirego.kmp.boilerplate.usecase.projectdetails.ProjectDetailsUseCase
import com.mirego.kmp.boilerplate.usecase.projectdetails.ProjectDetailsViewData
import com.mirego.kmp.boilerplate.viewmodel.common.ErrorViewModelImpl
import com.mirego.kmp.boilerplate.viewmodel.common.SharedImageResource
import com.mirego.kmp.boilerplate.viewmodel.navigation.NavigationManager
import com.mirego.kmp.boilerplate.viewmodel.navigation.NavigationRoute
import com.mirego.pilot.components.PilotButton
import com.mirego.pilot.components.PilotRemoteImage
import com.mirego.pilot.components.content.PilotLocalImageContent
import com.mirego.pilot.viewmodel.viewModelScope
import com.mirego.trikot.datasources.DataState
import com.mirego.trikot.kword.I18N
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory
import org.koin.core.annotation.InjectedParam
import org.koin.core.component.KoinComponent

@Factory
class ProjectDetailsViewModelImpl(
    projectDetailsUseCase: ProjectDetailsUseCase,
    private val i18N: I18N,
    @InjectedParam override val navigationManager: NavigationManager,
    @InjectedParam route: NavigationRoute.ProjectDetails
) : ProjectDetailsViewModel(), KoinComponent {
    private val navigationData = route.navigationData

    override val backgroundColor: RGBAColor = navigationData.backgroundColor
    override val textColor: RGBAColor = navigationData.textColor
    override val rootContent = projectDetailsUseCase.projectsDetails(navigationData.id).map { stateData ->
        when (stateData) {
            is DataState.Data -> buildContent(stateData.value, false)
            is DataState.Pending -> buildLoading()
            is DataState.Error -> buildError()
        }
    }.eagerlyStateIn(viewModelScope, buildLoading())

    override fun onAppear(coroutineScope: CoroutineScope) {
        super.onAppear(coroutineScope)
        Analytics.trackScreenView(ScreenName.project_details)
    }

    private fun buildContent(viewData: ProjectDetailsViewData, isLoading: Boolean) = ProjectDetailsRoot.Content(
        image = PilotRemoteImage(
            url = viewData.imageUrl,
            placeholder = SharedImageResource.imagePlaceholder
        ),
        title = viewData.title,
        subtitle = viewData.subtitle,
        projectType = Pair(
            i18N[KWordTranslation.PROJECT_DETAILS_PROJECT_TYPE],
            viewData.projectType
        ),
        releaseYear = Pair(
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
            retryAction = {}
        )
    )

    override val closeButton = PilotButton(
        content = stateFlowOf(
            PilotLocalImageContent(SharedImageResource.closeIcon)
        ),
        action = route.closeAction
    )
}
