package com.mirego.kmp.boilerplate.analytics

import com.mirego.trikot.analytics.AnalyticsEvent
import com.mirego.trikot.analytics.AnalyticsPropertiesType

class EmptySharedAnalyticsService : SharedAnalyticsService {
    override var isEnabled: Boolean = false

    override fun trackEvent(event: AnalyticsEvent, properties: AnalyticsPropertiesType) {
        // No-Op
    }
}
