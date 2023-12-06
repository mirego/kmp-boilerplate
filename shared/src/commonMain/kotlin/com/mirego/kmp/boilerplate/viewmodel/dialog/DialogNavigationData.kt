package com.mirego.kmp.boilerplate.viewmodel.dialog

import kotlinx.serialization.Serializable

@Serializable
data class DialogNavigationData(
    val title: String,
    val message: String,
    val buttons: List<DialogButtonData>
)

@Serializable
data class DialogButtonData(
    val title: String,
    val action: () -> Unit
)
