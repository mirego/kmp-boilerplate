package com.mirego.kmp.boilerplate.datasource

import com.apollographql.apollo3.ApolloClient
import com.mirego.kmp.boilerplate.ProjectsQuery
import com.mirego.kmp.boilerplate.bootstrap.ModuleQualifier
import com.mirego.kmp.boilerplate.datasource.apollo.ApolloGraphQLDataSource
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single

@Single
class ProjectsDataSource(
    apolloClient: ApolloClient,
    @Named(ModuleQualifier.DISK_CACHE_PATH) private val diskCachePath: String
) : ApolloGraphQLDataSource<ProjectsQuery.Data>(
    apolloClient = apolloClient,
    diskCachePath = "$diskCachePath/projects"
)
