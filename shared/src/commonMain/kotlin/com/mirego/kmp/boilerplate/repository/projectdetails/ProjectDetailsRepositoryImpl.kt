package com.mirego.kmp.boilerplate.repository.projectdetails

import com.mirego.kmp.boilerplate.ProjectDetailsQuery
import com.mirego.kmp.boilerplate.ProjectDetailsQuery.Data.Page.Companion.asPage
import com.mirego.kmp.boilerplate.ProjectDetailsQuery.Data.PagePage.Block.Companion.asProjectHeader
import com.mirego.kmp.boilerplate.adapter.ProjectDetailsQuery_ResponseAdapter
import com.mirego.kmp.boilerplate.datasource.ProjectDetailsDataSource
import com.mirego.kmp.boilerplate.datasource.apollo.ApolloGraphQLDataSourceRequest
import com.mirego.kmp.boilerplate.extension.mapToErrorIfValueNull
import com.mirego.kmp.boilerplate.utils.StateData
import com.mirego.trikot.datasources.flow.FlowDataSourceRequest
import com.mirego.trikot.datasources.flow.extensions.mapValue
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Single

@Single
class ProjectDetailsRepositoryImpl(
    private val dataSource: ProjectDetailsDataSource
) : ProjectDetailsRepository {

    override fun projectDetails(id: String): Flow<StateData<ProjectDetailsQuery.Data.PagePage.ProjectHeaderBlock.Entity>> = dataSource.read(
        ProjectDetailsQuery(id).request(forceRefresh = false)
    ).mapValue {
        it.value.page
            ?.asPage()
            ?.blocks?.firstNotNullOfOrNull {
                it.asProjectHeader()?.entity
            }
    }.mapToErrorIfValueNull()
}

private fun ProjectDetailsQuery.request(forceRefresh: Boolean = true) = ApolloGraphQLDataSourceRequest(
    query = this,
    serializeJsonMethod = ProjectDetailsQuery_ResponseAdapter.Data::toJson,
    deSerializeJsonMethod = ProjectDetailsQuery_ResponseAdapter.Data::fromJson,
    cacheableId = "${projectId.toString().replace("/", "-")}-${id()}",
    requestType = if (forceRefresh) {
        FlowDataSourceRequest.Type.REFRESH_CACHE
    } else {
        FlowDataSourceRequest.Type.USE_CACHE
    }
)
