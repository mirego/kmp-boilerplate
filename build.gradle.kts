@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.native.cocoapods) apply false
    alias(libs.plugins.serialization) apply false
    alias(libs.plugins.ktlint) apply false

    alias(libs.plugins.owasp.dependencycheck)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

dependencyCheck {
    formats = listOf("HTML", "SARIF")
    outputDirectory = "reports"

    analyzers.assemblyEnabled = false
    analyzers.experimentalEnabled = true
    analyzers.knownExploitedEnabled = true
}
