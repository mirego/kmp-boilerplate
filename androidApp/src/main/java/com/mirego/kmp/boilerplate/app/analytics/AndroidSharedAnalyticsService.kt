package com.mirego.kmp.boilerplate.app.analytics

import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.mirego.kmp.boilerplate.analytics.SharedAnalyticsService
import com.mirego.trikot.analytics.AnalyticsEvent
import com.mirego.trikot.analytics.AnalyticsPropertiesType

class AndroidSharedAnalyticsService(
    context: Context,
    private var analyticsEnabled: Boolean = true
) : SharedAnalyticsService {
    private var firebaseAnalytics = FirebaseAnalytics.getInstance(context)

    override var isEnabled: Boolean
        get() = analyticsEnabled
        set(value) {
            analyticsEnabled = value
            firebaseAnalytics.setAnalyticsCollectionEnabled(value)
        }

    override fun trackEvent(event: AnalyticsEvent, properties: AnalyticsPropertiesType) {
        val bundle = Bundle()
        properties.forEach {
            bundle.putString(it.key, it.value.toString())
        }
        firebaseAnalytics.logEvent(event.name, bundle)
    }
}
