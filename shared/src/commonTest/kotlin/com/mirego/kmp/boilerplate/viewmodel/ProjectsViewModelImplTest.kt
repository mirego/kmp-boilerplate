package com.mirego.kmp.boilerplate.viewmodel

import com.mirego.kmp.boilerplate.testutils.BaseTest
import com.mirego.kmp.boilerplate.usecase.projectdetails.toVMDColor
import com.mirego.kmp.boilerplate.usecase.projects.ProjectItemViewData
import com.mirego.kmp.boilerplate.usecase.projects.ProjectsUseCase
import com.mirego.kmp.boilerplate.usecase.projects.ProjectsViewData
import com.mirego.kmp.boilerplate.utils.stateDataData
import com.mirego.kmp.boilerplate.viewmodel.factory.ViewModelFactory
import com.mirego.kmp.boilerplate.viewmodel.projects.ProjectsContentSection
import com.mirego.kmp.boilerplate.viewmodel.projects.ProjectsRoot
import com.mirego.kmp.boilerplate.viewmodel.projects.ProjectsViewModelImpl
import com.mirego.trikot.viewmodels.declarative.properties.VMDColor
import io.mockk.every
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest

class ProjectsViewModelImplTest : BaseTest() {
    private val useCase = mockk<ProjectsUseCase>()
    private val viewModelFactory = mockk<ViewModelFactory>()

    private val viewModel by lazy {
        ProjectsViewModelImpl(
            useCase,
            i18N,
            viewModelFactory,
            testCoroutineScope
        )
    }

    @Test
    fun `when an Empty view data is returned then proper sections are displayed`() = runTest {
        val viewData = ProjectsViewData.Empty
        every { useCase.projects() } returns flowOf(stateDataData(viewData))

        val sections = (viewModel.rootContent as ProjectsRoot.Content).sections.elements
        assertTrue { sections[0] is ProjectsContentSection.Header }
        assertTrue { sections[1] is ProjectsContentSection.NoProjects }
    }

    @Test
    fun `when an Content view data is returned then proper sections are displayed`() = runTest {
        val viewData = ProjectsViewData.Content(
            listOf(
                ProjectItemViewData(
                    id = "id",
                    title = "title",
                    subtitle = "subtitle",
                    description = "description",
                    imageUrl = "imageUrl",
                    backgroundColor = "000000".toVMDColor() ?: VMDColor.None,
                    textColor = "FFFFFF".toVMDColor() ?: VMDColor.None
                )
            )
        )
        every { useCase.projects() } returns flowOf(stateDataData(viewData))

        val sections = (viewModel.rootContent as ProjectsRoot.Content).sections.elements
        assertTrue { sections[0] is ProjectsContentSection.Header }
        assertTrue { sections[1] is ProjectsContentSection.ProjectsList }
    }
}
