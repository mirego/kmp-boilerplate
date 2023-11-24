package com.mirego.kmp.boilerplate.repository.projectdetails

import com.mirego.kmp.boilerplate.ProjectDetailsQuery
import com.mirego.kmp.boilerplate.utils.StateData
import kotlinx.coroutines.flow.Flow

interface ProjectDetailsRepository {
    fun projectDetails(id: String): Flow<StateData<ProjectDetailsQuery.Data.PagePage.ProjectHeaderBlock.Entity>>
}
