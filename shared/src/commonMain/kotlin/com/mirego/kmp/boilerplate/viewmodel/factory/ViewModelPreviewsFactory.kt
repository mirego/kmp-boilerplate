package com.mirego.kmp.boilerplate.viewmodel.factory

import com.mirego.kmp.boilerplate.model.RGBAColor
import com.mirego.kmp.boilerplate.usecase.preview.PreviewState
import com.mirego.kmp.boilerplate.usecase.preview.UseCaseFactoryPreview
import com.mirego.kmp.boilerplate.usecase.projectdetails.toRGBAColor
import com.mirego.kmp.boilerplate.viewmodel.application.ApplicationViewModel
import com.mirego.kmp.boilerplate.viewmodel.application.ApplicationViewModelImpl
import com.mirego.kmp.boilerplate.viewmodel.navigation.NavigationManager
import com.mirego.kmp.boilerplate.viewmodel.navigation.NavigationRoute
import com.mirego.kmp.boilerplate.viewmodel.projectdetails.ProjectDetailsNavigationData
import com.mirego.kmp.boilerplate.viewmodel.projectdetails.ProjectDetailsViewModelImpl
import com.mirego.kmp.boilerplate.viewmodel.projects.ProjectsViewModelImpl
import com.mirego.kmp.boilerplate.viewmodel.root.RootViewModel
import com.mirego.kmp.boilerplate.viewmodel.root.RootViewModelImpl
import com.mirego.trikot.kword.I18N
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.component.KoinComponent

@Suppress("unused", "MemberVisibilityCanBePrivate")
class ViewModelPreviewsFactory(
    private val i18N: I18N,
    private val useCaseFactoryPreview: UseCaseFactoryPreview = UseCaseFactoryPreview()
) : KoinComponent {
    private val navigationManager = NavigationManager(
        coroutineScope = createCoroutineScope(),
    )

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler got $exception")
    }

    private fun createCoroutineScope() = CoroutineScope(Dispatchers.Main.immediate + SupervisorJob() + coroutineExceptionHandler)

    fun createApplication(): ApplicationViewModel = ApplicationViewModelImpl()

    fun createRoot(): RootViewModel = RootViewModelImpl(navigationManager)

    fun createProjects(previewState: PreviewState = PreviewState.Data.Content) = ProjectsViewModelImpl(
        projectsUseCase = useCaseFactoryPreview.projectsUseCase(previewState),
        i18N = i18N,
        navigationManager = navigationManager
    )

    fun createProjectDetails(previewState: PreviewState = PreviewState.Data.Content) = ProjectDetailsViewModelImpl(
        projectDetailsUseCase = useCaseFactoryPreview.projectDetailsUseCase(previewState),
        i18N = i18N,
        navigationManager = navigationManager,
        route = NavigationRoute.ProjectDetails(
            ProjectDetailsNavigationData(
                "",
                "000000".toRGBAColor() ?: RGBAColor.None,
                "ffffff".toRGBAColor() ?: RGBAColor.None
            )
        )
    )
}
