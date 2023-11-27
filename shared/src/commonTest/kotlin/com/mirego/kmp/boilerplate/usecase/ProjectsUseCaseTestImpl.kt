package com.mirego.kmp.boilerplate.usecase

import com.mirego.kmp.boilerplate.ProjectsQuery
import com.mirego.kmp.boilerplate.repository.projects.ProjectsRepository
import com.mirego.kmp.boilerplate.testutils.BaseTest
import com.mirego.kmp.boilerplate.usecase.projects.ProjectsUseCaseImpl
import com.mirego.kmp.boilerplate.usecase.projects.ProjectsViewData
import com.mirego.kmp.boilerplate.utils.stateDataData
import com.mirego.trikot.datasources.extensions.value
import io.mockk.every
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest

class ProjectsUseCaseTestImpl : BaseTest() {
    private val repository = mockk<ProjectsRepository>()
    private val useCase by lazy {
        ProjectsUseCaseImpl(
            repository
        )
    }

    @Test
    fun `when there are no projects returned then ProjectsViewData Empty is returned`() = runTest {
        every { repository.projects() } returns flowOf(stateDataData(emptyList()))
        val cart = useCase.projects().first().value() as ProjectsViewData

        assertTrue {
            cart is ProjectsViewData.Empty
        }
    }

    @Test
    fun `when multiple projects are returned then ProjectsViewData Content is returned`() = runTest {
        every { repository.projects() } returns flowOf(
            stateDataData(
                listOf(
                    mockItem(),
                    mockItem(),
                    mockItem()
                )
            )
        )
        val cart = useCase.projects().first().value() as ProjectsViewData

        assertTrue {
            cart is ProjectsViewData.Content
        }
    }

    @Test
    fun `when one project is returned then ProjectsViewData Content is returned`() = runTest {
        every { repository.projects() } returns flowOf(
            stateDataData(
                listOf(
                    mockItem()
                )
            )
        )
        val cart = useCase.projects().first().value() as ProjectsViewData

        assertTrue {
            cart is ProjectsViewData.Content
        }
    }

    private fun mockItem() = ProjectsQuery.Data.PagePage.ProjectsListBlock.Projects.Entry(
        pageSlug = "work/mirego",
        name = "Mirego Projects",
        projectType = "Application",
        listImageUrl = "https://miregologo.com",
        client = ProjectsQuery.Data.PagePage.ProjectsListBlock.Projects.Entry.Client(
            name = "Mirego"
        ),
        "000000",
        "FFFFFF"
    )
}
