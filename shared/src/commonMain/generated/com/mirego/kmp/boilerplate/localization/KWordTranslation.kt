package com.mirego.kmp.boilerplate.localization

import com.mirego.trikot.kword.KWordKey
import kotlin.String

enum class KWordTranslation(
    override val translationKey: String
) : KWordKey {
    GENERIC_ERROR_MESSAGE("generic_error_message"),

    GENERIC_ERROR_TITLE("generic_error_title"),

    PROJECTS_EMPTY_CONTENT_MESSAGE("projects_empty_content_message"),

    GENERIC_EMPTY_CONTENT_TITLE("generic_empty_content_title"),

    GENERIC_RETRY("generic_retry");
}
