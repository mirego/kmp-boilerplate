package com.mirego.kmp.boilerplate.analytics

import com.mirego.trikot.analytics.AnalyticsEvent
import com.mirego.trikot.analytics.AnalyticsPropertiesType

interface SharedAnalyticsService {
    var isEnabled: Boolean
    fun trackEvent(event: AnalyticsEvent, properties: AnalyticsPropertiesType)
}
