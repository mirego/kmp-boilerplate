package com.mirego.kmp.boilerplate.datasource.generic

import com.mirego.kmp.boilerplate.app.coroutines.Dispatchers
import com.mirego.kmp.boilerplate.app.filesystem.NativeFileSystem
import com.mirego.trikot.datasources.flow.BaseExpiringExecutableFlowDataSource
import com.mirego.trikot.datasources.flow.ExpiringFlowDataSourceRequest
import com.mirego.trikot.datasources.flow.FlowDataSourceExpiringValue
import kotlinx.coroutines.withContext
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import okio.FileNotFoundException
import okio.Path
import okio.Path.Companion.toPath

class GenericDiskDataSource<R : ExpiringFlowDataSourceRequest, T>(
    private val json: Json,
    private val dataSerializer: KSerializer<T>,
    private val diskCachePath: String
) : BaseExpiringExecutableFlowDataSource<R, T>() {

    private val fileManager = NativeFileSystem.fileSystem

    override suspend fun internalRead(request: R): FlowDataSourceExpiringValue<T> {
        return withContext(Dispatchers.IO) {
            val filePath = buildFilePath(request)
            try {
                val data = json.decodeFromString(dataSerializer, getFileAsString(filePath))
                FlowDataSourceExpiringValue(
                    value = data,
                    expiredEpoch = (NativeFileSystem.fileSystem.metadata(filePath).lastModifiedAtMillis ?: 0) + request.expiredInMilliseconds
                )
            } catch (exception: SerializationException) {
                fileManager.deleteRecursively(filePath)
                throw exception
            }
        }
    }

    override suspend fun save(request: R, data: FlowDataSourceExpiringValue<T>?) {
        data?.value?.let { dataToSave ->
            withContext(Dispatchers.IO) {
                try {
                    val filePath = buildFilePath(request)
                    saveStringValueToFile(filePath, json.encodeToString(dataSerializer, dataToSave))
                } catch (exception: Throwable) {
                    println("Failed to save json to disk cache. Error: $exception")
                }
            }
        }
    }

    private fun getFileAsString(filePath: Path) = if (fileManager.exists(filePath)) {
        fileManager.read(filePath) {
            readUtf8()
        }
    } else {
        throw FileNotFoundException("File at path $filePath does not exist")
    }

    private fun saveStringValueToFile(filePath: Path, value: String) {
        filePath.parent?.let { fileManager.createDirectories(it) }
        fileManager.write(filePath) {
            writeUtf8(value)
        }
    }

    private fun buildFilePath(request: R) = "$diskCachePath/${request.cacheableId}.json".toPath()
}
