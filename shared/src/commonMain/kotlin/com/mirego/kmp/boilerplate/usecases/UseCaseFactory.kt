package com.mirego.kmp.boilerplate.usecases

import com.mirego.kmp.boilerplate.repositories.RepositoryFactory
import com.mirego.kmp.boilerplate.usecases.example.ExampleUseCase
import com.mirego.kmp.boilerplate.usecases.example.ExampleUseCaseImpl

class UseCaseFactory(
    private val repositoryFactory: RepositoryFactory
) {
    val exampleUseCase: ExampleUseCase
        get() = ExampleUseCaseImpl(
            repositoryFactory.exampleRepository
        )
}
