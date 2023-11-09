package com.mirego.kmp.boilerplate.usecases.example

import com.mirego.kmp.boilerplate.repositories.example.ExampleRepository
import kotlinx.coroutines.flow.Flow

interface ExampleUseCase {
    val items: Flow<List<String>>

    suspend fun addItem(item: String)
}

class ExampleUseCaseImpl(
    private val exampleRepository: ExampleRepository
) : ExampleUseCase {
    override val items = exampleRepository.items

    override suspend fun addItem(item: String) = exampleRepository.addItem(item)
}
