package com.mirego.kmp.boilerplate.datasource.apollo

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.CustomScalarAdapters
import com.apollographql.apollo3.api.Query
import com.apollographql.apollo3.api.json.JsonReader
import com.apollographql.apollo3.api.json.JsonWriter
import com.mirego.kmp.boilerplate.datasource.DataSourceUtils
import com.mirego.trikot.datasources.flow.BaseExpiringExecutableFlowDataSource
import com.mirego.trikot.datasources.flow.ExpiringFlowDataSourceRequest
import com.mirego.trikot.datasources.flow.FlowDataSourceExpiringValue
import com.mirego.trikot.datasources.flow.FlowDataSourceRequest
import kotlin.reflect.KFunction2
import kotlin.reflect.KFunction3

open class ApolloGraphQLDataSource<T : Query.Data>(
    private val apolloClient: ApolloClient,
    diskCachePath: String?
) : BaseExpiringExecutableFlowDataSource<ApolloGraphQLDataSourceRequest<T>, T>(
    diskCachePath?.let { ApolloGraphQLDiskDataSource(diskCachePath) }
) {

    override suspend fun internalRead(request: ApolloGraphQLDataSourceRequest<T>): FlowDataSourceExpiringValue<T> {
        val response = apolloClient
            .query(request.query)
            .execute()
        return DataSourceUtils.buildExpiringValue(response.dataAssertNoErrors, request)
    }
}

interface BaseApolloGraphQLDataSourceRequest<D : Query.Data> : ExpiringFlowDataSourceRequest {
    val query: Query<D>
}

interface ApolloGraphQLDiskDataSourceRequest<D : Query.Data> : BaseApolloGraphQLDataSourceRequest<D> {
    val serializeJsonMethod: KFunction3<JsonWriter, CustomScalarAdapters, D, Unit>
    val deSerializeJsonMethod: KFunction2<JsonReader, CustomScalarAdapters, D>
}

data class ApolloGraphQLDataSourceRequest<D : Query.Data>(
    override val query: Query<D>,
    override val serializeJsonMethod: KFunction3<JsonWriter, CustomScalarAdapters, D, Unit>,
    override val deSerializeJsonMethod: KFunction2<JsonReader, CustomScalarAdapters, D>,
    override val cacheableId: String,
    override val requestType: FlowDataSourceRequest.Type = FlowDataSourceRequest.Type.USE_CACHE,
    override val expiredInMilliseconds: Long = 1_000 * 10
) : ApolloGraphQLDiskDataSourceRequest<D>
