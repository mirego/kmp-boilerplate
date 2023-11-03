package com.mirego.kmp.boilerplate.datasource.apollo

import com.apollographql.apollo3.api.CustomScalarAdapters
import com.apollographql.apollo3.api.Query
import com.apollographql.apollo3.api.json.BufferedSinkJsonWriter
import com.apollographql.apollo3.api.json.BufferedSourceJsonReader
import com.mirego.kmp.boilerplate.app.coroutines.Dispatchers
import com.mirego.kmp.boilerplate.app.filesystem.NativeFileSystem
import com.mirego.trikot.datasources.flow.BaseExpiringExecutableFlowDataSource
import com.mirego.trikot.datasources.flow.FlowDataSourceExpiringValue
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import okio.Path.Companion.toPath
import okio.buffer

class ApolloGraphQLDiskDataSource<R : ApolloGraphQLDataSourceRequest<T>, T : Query.Data>(
    private val diskCachePath: String
) : BaseExpiringExecutableFlowDataSource<R, T>() {
    companion object {
        private val customScalarAdapters = CustomScalarAdapters.Builder().build()
    }

    private val fileManager = NativeFileSystem.fileSystem

    override suspend fun internalRead(request: R): FlowDataSourceExpiringValue<T> {
        return withContext(Dispatchers.IO) {
            val filePath = buildFilePath(request)
            try {
                if (NativeFileSystem.fileSystem.exists(filePath)) {
                    val buffer = NativeFileSystem.fileSystem.source(filePath).buffer()
                    val jsonReader = BufferedSourceJsonReader(buffer)
                    jsonReader.beginObject()
                    val result = request.deSerializeJsonMethod(jsonReader, customScalarAdapters)
                    jsonReader.endObject()

                    FlowDataSourceExpiringValue(
                        value = result,
                        expiredEpoch = (NativeFileSystem.fileSystem.metadata(filePath).lastModifiedAtMillis ?: 0) + request.expiredInMilliseconds
                    )
                } else {
                    throw Throwable("Cache file doesn't exist: ${filePath.name} ")
                }
            } catch (exception: SerializationException) {
                fileManager.deleteRecursively(filePath)
                throw exception
            }
        }
    }

    override suspend fun save(request: R, data: FlowDataSourceExpiringValue<T>?) {
        data?.value?.let { dataToSave ->
            withContext(Dispatchers.IO) {
                val filePath = buildFilePath(request)
                try {
                    NativeFileSystem.fileSystem.createDirectories(diskCachePath.toPath())
                    val content = NativeFileSystem.fileSystem.sink(file = filePath, mustCreate = true).buffer()
                    val jsonWritter = BufferedSinkJsonWriter(content)
                    jsonWritter.beginObject()
                    request.serializeJsonMethod(jsonWritter, customScalarAdapters, dataToSave)
                    jsonWritter.endObject()
                    content.flush()
                    content.close()
                } catch (error: Throwable) {
                    println("Failed to save json in disk cache, $filePath : $error")
                }
            }
        }
    }

    private fun buildFilePath(request: R) = "$diskCachePath/${request.cacheableId}.json".toPath()
}
