package com.mirego.kmp.boilerplate.repository.projects

import com.mirego.kmp.boilerplate.ProjectsQuery
import com.mirego.kmp.boilerplate.ProjectsQuery.Data.Page.Companion.asPage
import com.mirego.kmp.boilerplate.ProjectsQuery.Data.PagePage.Block.Companion.asProjectsList
import com.mirego.kmp.boilerplate.adapter.ProjectsQuery_ResponseAdapter
import com.mirego.kmp.boilerplate.datasource.ProjectsDataSource
import com.mirego.kmp.boilerplate.datasource.apollo.ApolloGraphQLDataSourceRequest
import com.mirego.kmp.boilerplate.model.Language
import com.mirego.kmp.boilerplate.repository.locale.LocaleRepository
import com.mirego.kmp.boilerplate.utils.StateData
import com.mirego.trikot.datasources.flow.FlowDataSourceRequest
import com.mirego.trikot.datasources.flow.extensions.mapValue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import org.koin.core.annotation.Single

@Single
class ProjectsRepositoryImpl(
    private val localeRepository: LocaleRepository,
    private val dataSource: ProjectsDataSource
) : ProjectsRepository {
    override fun projects(): Flow<StateData<List<ProjectsQuery.Data.PagePage.ProjectsListBlock.Projects.Entry>>> = localeRepository.locale()
        .flatMapLatest { locale ->
            dataSource.read(
                buildQuery(language = locale.language)
                    .request(forceRefresh = false)
            ).mapValue {
                it.value.page
                    ?.asPage()
                    ?.blocks?.firstNotNullOfOrNull {
                        it.asProjectsList()?.projects?.entries
                    }.orEmpty()
            }
        }

    override suspend fun refreshProjects() {
        val locale = localeRepository.locale().first()
        dataSource.read(
            buildQuery(language = locale.language)
                .request(forceRefresh = true)
        ).filter { !it.isPending() }.first()
    }

    private fun buildQuery(language: Language) = ProjectsQuery(
        when (language) {
            Language.ENGLISH -> "work"
            Language.FRENCH -> "projets"
        }
    )
}

private fun ProjectsQuery.request(forceRefresh: Boolean = true) = ApolloGraphQLDataSourceRequest(
    query = this,
    serializeJsonMethod = ProjectsQuery_ResponseAdapter.Data::toJson,
    deSerializeJsonMethod = ProjectsQuery_ResponseAdapter.Data::fromJson,
    cacheableId = id() + projectsSlug.toString(),
    requestType = if (forceRefresh) FlowDataSourceRequest.Type.REFRESH_CACHE else FlowDataSourceRequest.Type.USE_CACHE
)
