package com.mirego.kmp.boilerplate.datasources

import com.mirego.kmp.boilerplate.datasources.example.ExampleDataSource
import com.mirego.kmp.boilerplate.datasources.example.LocalExampleDataSource

class DataSourceFactory {
    val exampleDataSource: ExampleDataSource get() = LocalExampleDataSource()
}
