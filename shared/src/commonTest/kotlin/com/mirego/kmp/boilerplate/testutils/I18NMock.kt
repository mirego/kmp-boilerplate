package com.mirego.kmp.boilerplate.testutils

import com.mirego.trikot.kword.I18N
import com.mirego.trikot.kword.KWordKey
import com.mirego.trikot.kword.KWordSource

class I18NMock(var withArgs: Boolean = false) : I18N {

    override fun changeLocaleStrings(source: KWordSource) {
    }

    override fun changeLocaleStrings(strings: Map<String, String>) {
    }

    override fun get(key: KWordKey): String {
        return key.translationKey
    }

    override fun t(key: KWordKey): String {
        return key.translationKey
    }

    override fun t(key: KWordKey, vararg arguments: Pair<String, String>): String {
        return if (withArgs) {
            key.translationKey + "[${arguments.joinToString { "${it.first}: '${it.second}'" }}]"
        } else {
            key.translationKey
        }
    }

    override fun t(key: KWordKey, count: Int, vararg arguments: Pair<String, String>): String {
        return if (withArgs) {
            "${key.translationKey} $count [${arguments.joinToString { "'$it'" }}]"
        } else {
            "${key.translationKey} $count"
        }
    }
}
