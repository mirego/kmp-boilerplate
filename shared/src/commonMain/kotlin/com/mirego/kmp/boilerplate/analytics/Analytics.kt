@file:Suppress("EnumEntryName")

package com.mirego.kmp.boilerplate.analytics

import com.mirego.trikot.analytics.AnalyticsConfiguration
import com.mirego.trikot.analytics.AnalyticsEvent

object Analytics {
    private const val PARAM_SCREEN_TITLE = "screen_title"
    private const val PARAM_PROJECT_ID = "project_id"

    fun trackScreenView(screen: ScreenName) {
        AnalyticsConfiguration.analyticsManager.trackEvent(
            event = AnalyticsEvents.screen_view,
            properties = mapOf(
                PARAM_SCREEN_TITLE to screen.name
            )
        )
    }

    fun trackViewProject(projectId: String) {
        AnalyticsConfiguration.analyticsManager.trackEvent(
            event = AnalyticsEvents.view_project,
            properties = mapOf(
                PARAM_PROJECT_ID to projectId
            )
        )
    }
}

enum class AnalyticsEvents : AnalyticsEvent {
    screen_view,
    view_project
}

enum class ScreenName {
    projects,
    project_details
}
