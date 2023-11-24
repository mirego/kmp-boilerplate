package com.mirego.kmp.boilerplate.usecase.preview

sealed interface PreviewState {
    sealed interface Data : PreviewState {
        data object Content : Data
        data object Empty : Data
    }

    data object Loading : PreviewState
    data object Error : PreviewState
}
