package com.mirego.kmp.boilerplate.datasource

import com.mirego.trikot.datasources.flow.ExpiringFlowDataSourceRequest
import com.mirego.trikot.datasources.flow.FlowDataSourceExpiringValue
import com.mirego.trikot.foundation.date.Date

object DataSourceUtils {
    fun <T> buildExpiringValue(value: T, request: ExpiringFlowDataSourceRequest) = FlowDataSourceExpiringValue(value, Date.now.epoch + request.expiredInMilliseconds)
}
