package com.mirego.kmp.boilerplate.repositories

import com.mirego.kmp.boilerplate.datasources.DataSourceFactory
import com.mirego.kmp.boilerplate.repositories.example.ExampleRepository
import com.mirego.kmp.boilerplate.repositories.example.ExampleRepositoryImpl

class RepositoryFactory(
    dataSourceFactory: DataSourceFactory
) {
    val exampleRepository: ExampleRepository by lazy {
        ExampleRepositoryImpl(
            dataSourceFactory.exampleDataSource
        )
    }
}
