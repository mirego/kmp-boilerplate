package com.mirego.kmp.boilerplate

import com.mirego.kmp.boilerplate.datasources.DataSourceFactory
import com.mirego.kmp.boilerplate.repositories.RepositoryFactory
import com.mirego.kmp.boilerplate.usecases.UseCaseFactory
import com.mirego.kmp.boilerplate.viewmodels.ViewModelFactory

interface Bootstrapper {
    val viewModelFactory: ViewModelFactory
}

class AppBootstrapper : Bootstrapper {
    private val dataSourceFactory: DataSourceFactory = DataSourceFactory()
    private val repositoryFactory: RepositoryFactory = RepositoryFactory(dataSourceFactory)
    private val useCaseFactory: UseCaseFactory = UseCaseFactory(repositoryFactory)

    override val viewModelFactory: ViewModelFactory = ViewModelFactory(useCaseFactory)
}
