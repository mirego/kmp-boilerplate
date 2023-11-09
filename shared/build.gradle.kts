@file:Suppress("UNUSED_VARIABLE")

import co.touchlab.skie.configuration.DefaultArgumentInterop
import co.touchlab.skie.configuration.EnumInterop
import co.touchlab.skie.configuration.ExperimentalFeatures
import co.touchlab.skie.configuration.FlowInterop
import co.touchlab.skie.configuration.SealedInterop
import co.touchlab.skie.configuration.SuspendInterop

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.native.cocoapods)
    alias(libs.plugins.serialization)
    alias(libs.plugins.android.library)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.skie)
}

version = project.property("versionName") as String

kotlin {
    jvmToolchain(17)

    androidTarget {
        publishAllLibraryVariants()
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Project summary"
        homepage = "https://github.com/mirego/your-project"
        name = "Shared"
        podfile = project.file("../ios/Podfile")

        framework {
            baseName = "Shared"
        }
    }

    sourceSets {
        all {
            languageSettings {
                optIn("kotlinx.coroutines.ExperimentalCoroutinesApi")
            }
        }

        androidMain {
            dependencies {
                implementation(libs.androidx.startup.runtime)
            }
        }

        commonMain {
            dependencies {
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.kotlinx.serialization.json)
                implementation(libs.skie.configuration.annotations)
            }
        }

        commonTest {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.kotlinx.coroutines.test)
            }
        }
    }
}

android {
    namespace = "com.mirego.kmp.boilerplate.common"
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

    compileSdk = 34
    defaultConfig {
        minSdk = 21

        buildConfigField("Integer", "VERSION_CODE", "${project.property("versionCode")}")
        buildConfigField("String", "VERSION_NAME", "\"$version\"")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

ktlint {
    android.set(true)
    enableExperimentalRules.set(true)
    filter {
        exclude { element -> element.file.path.contains("generated/") }
    }
}

skie {
    analytics {
        enabled.set(false)
    }
    features {
        group {
            DefaultArgumentInterop.Enabled(false)
            EnumInterop.Enabled(false)
            ExperimentalFeatures.Enabled(false)
            FlowInterop.Enabled(false)
            SealedInterop.Enabled(false)
            SuspendInterop.Enabled(false)
        }
        group("com.mirego.kmp.boilerplate.viewmodels.ViewModelFactory") {
            DefaultArgumentInterop.Enabled(true)
            DefaultArgumentInterop.MaximumDefaultArgumentCount(1)
        }
        group("com.mirego.kmp.boilerplate.viewmodels") {
            EnumInterop.Enabled(true)
            FlowInterop.Enabled(true)
            SealedInterop.Enabled(true)
            SuspendInterop.Enabled(true)
        }
    }
}
