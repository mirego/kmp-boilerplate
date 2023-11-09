package com.mirego.kmp.boilerplate.datasources.example

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

interface ExampleDataSource {
    val items: Flow<List<String>>

    suspend fun addItem(item: String)
}

class LocalExampleDataSource : ExampleDataSource {
    override val items = MutableStateFlow(
        (1..3).map { "Item $it" }
    )

    override suspend fun addItem(item: String) {
        items.value += item
    }
}
