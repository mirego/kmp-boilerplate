package com.mirego.kmp.boilerplate.model

class RGBAColor(val red: Int, val green: Int, val blue: Int, val alpha: Float = 1.0f) {
    companion object {
        val None = RGBAColor(-1, -1, -1, -1f)
    }
}
