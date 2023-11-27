package com.mirego.kmp.boilerplate.localization

import com.mirego.trikot.kword.KWordKey
import kotlin.String

enum class KWordTranslation(
    override val translationKey: String
) : KWordKey {
    PROJECT_DETAILS_RELEASE_YEAR("project_details_release_year"),

    GENERIC_ERROR_MESSAGE("generic_error_message"),

    GENERIC_ERROR_TITLE("generic_error_title"),

    PROJECTS_HEADER_DESCRIPTION("projects_header_description"),

    PROJECTS_EMPTY_CONTENT_MESSAGE("projects_empty_content_message"),

    PROJECTS_HEADER_TITLE("projects_header_title"),

    GENERIC_EMPTY_CONTENT_TITLE("generic_empty_content_title"),

    GENERIC_RETRY("generic_retry"),

    PROJECT_DETAILS_PROJECT_TYPE("project_details_project_type");
}
