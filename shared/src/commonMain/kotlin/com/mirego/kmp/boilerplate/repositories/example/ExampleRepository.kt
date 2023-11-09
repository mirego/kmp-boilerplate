package com.mirego.kmp.boilerplate.repositories.example

import com.mirego.kmp.boilerplate.datasources.example.ExampleDataSource
import kotlinx.coroutines.flow.Flow

interface ExampleRepository {
    val items: Flow<List<String>>

    suspend fun addItem(item: String)
}

class ExampleRepositoryImpl(
    private val exampleDataSource: ExampleDataSource
) : ExampleRepository {
    override val items = exampleDataSource.items

    override suspend fun addItem(item: String) = exampleDataSource.addItem(item)
}
