package com.mirego.kmp.boilerplate.usecase.preview

class UseCaseFactoryPreview {
    fun projectsUseCase(previewState: PreviewState) = ProjectsUseCasePreview(previewState)
    fun projectDetailsUseCase(previewState: PreviewState) = ProjectDetailsUseCasePreview(previewState)
}
