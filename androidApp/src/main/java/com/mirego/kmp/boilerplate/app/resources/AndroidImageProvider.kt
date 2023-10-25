package com.mirego.kmp.boilerplate.app.resources

import android.content.Context
import com.mirego.trikot.viewmodels.declarative.configuration.VMDImageProvider
import com.mirego.trikot.viewmodels.declarative.properties.VMDImageResource

class AndroidImageProvider : VMDImageProvider {
    override fun resourceIdForResource(resource: VMDImageResource, context: Context) = null
}
