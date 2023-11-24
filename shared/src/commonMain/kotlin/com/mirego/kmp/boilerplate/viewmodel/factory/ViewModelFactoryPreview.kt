package com.mirego.kmp.boilerplate.viewmodel.factory

import com.mirego.kmp.boilerplate.usecase.preview.PreviewState
import com.mirego.kmp.boilerplate.usecase.preview.UseCaseFactoryPreview
import com.mirego.kmp.boilerplate.usecase.projectdetails.toVMDColor
import com.mirego.kmp.boilerplate.viewmodel.application.ApplicationViewModelImpl
import com.mirego.kmp.boilerplate.viewmodel.projectdetails.ProjectDetailsNavigationData
import com.mirego.kmp.boilerplate.viewmodel.projectdetails.ProjectDetailsViewModelImpl
import com.mirego.kmp.boilerplate.viewmodel.projects.ProjectsViewModelImpl
import com.mirego.kmp.boilerplate.viewmodel.root.RootViewModelImpl
import com.mirego.trikot.kword.I18N
import com.mirego.trikot.viewmodels.declarative.properties.VMDColor
import com.mirego.trikot.viewmodels.declarative.util.CoroutineScopeProvider
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope

@Suppress("unused", "MemberVisibilityCanBePrivate")
class ViewModelFactoryPreview(
    private val i18N: I18N,
    private val useCaseFactoryPreview: UseCaseFactoryPreview = UseCaseFactoryPreview()
) : ViewModelFactory {

    fun createCoroutineScope() = CoroutineScopeProvider.provideMainWithSuperviserJob(
        CoroutineExceptionHandler { _, exception ->
            println("CoroutineExceptionHandler got $exception")
        }
    )

    fun createApplication() = ApplicationViewModelImpl(
        this,
        createCoroutineScope()
    )

    override fun createRoot(coroutineScope: CoroutineScope) = createRoot()

    fun createRoot() = RootViewModelImpl(
        i18N = i18N,
        viewModelFactory = this,
        coroutineScope = createCoroutineScope()
    )

    override fun createProjects(coroutineScope: CoroutineScope) = createProjects()
    fun createProjects(previewState: PreviewState = PreviewState.Data.Content) = ProjectsViewModelImpl(
        projectsUseCase = useCaseFactoryPreview.projectsUseCase(previewState),
        i18N = i18N,
        viewModelFactory = this,
        coroutineScope = createCoroutineScope()
    )

    override fun createProjectDetails(navigationData: ProjectDetailsNavigationData, closeAction: () -> Unit, coroutineScope: CoroutineScope) = createProjectDetails()

    fun createProjectDetails(previewState: PreviewState = PreviewState.Data.Content) = ProjectDetailsViewModelImpl(
        navigationData = ProjectDetailsNavigationData("", "000000".toVMDColor() ?: VMDColor.None, "ffffff".toVMDColor() ?: VMDColor.None),
        projectDetailsUseCase = useCaseFactoryPreview.projectDetailsUseCase(previewState),
        i18N = i18N,
        viewModelFactory = this,
        closeAction = {},
        coroutineScope = createCoroutineScope()
    )
}
