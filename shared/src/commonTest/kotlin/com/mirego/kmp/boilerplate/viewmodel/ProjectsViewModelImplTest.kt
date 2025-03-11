package com.mirego.kmp.boilerplate.viewmodel

import com.mirego.kmp.boilerplate.model.RGBAColor
import com.mirego.kmp.boilerplate.testutils.BaseTest
import com.mirego.kmp.boilerplate.usecase.projectdetails.toRGBAColor
import com.mirego.kmp.boilerplate.usecase.projects.ProjectItemViewData
import com.mirego.kmp.boilerplate.usecase.projects.ProjectsUseCase
import com.mirego.kmp.boilerplate.usecase.projects.ProjectsViewData
import com.mirego.kmp.boilerplate.utils.stateDataData
import com.mirego.kmp.boilerplate.viewmodel.navigation.NavigationManager
import com.mirego.kmp.boilerplate.viewmodel.projects.ProjectsContentSection
import com.mirego.kmp.boilerplate.viewmodel.projects.ProjectsRoot
import com.mirego.kmp.boilerplate.viewmodel.projects.ProjectsViewModelImpl
import io.mockk.every
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest

class ProjectsViewModelImplTest : BaseTest() {
    private val useCase = mockk<ProjectsUseCase>()
    private val navigationManager = mockk<NavigationManager>()

    private val viewModel by lazy {
        ProjectsViewModelImpl(
            useCase,
            i18N,
            navigationManager
        )
    }

    @Test
    fun `when an Empty view data is returned then proper sections are displayed`() = runTest {
        val viewData = ProjectsViewData.Empty
        every { useCase.projects() } returns flowOf(stateDataData(viewData))

        val sections = (viewModel.rootContent as ProjectsRoot.Content).sections
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
                    backgroundColor = "000000".toRGBAColor() ?: RGBAColor.None,
                    textColor = "FFFFFF".toRGBAColor() ?: RGBAColor.None
                )
            )
        )
        every { useCase.projects() } returns flowOf(stateDataData(viewData))

        val sections = (viewModel.rootContent as ProjectsRoot.Content).sections
        assertTrue { sections[0] is ProjectsContentSection.Header }
        assertTrue { sections[1] is ProjectsContentSection.ProjectsList }
    }
}
