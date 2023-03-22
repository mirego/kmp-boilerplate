@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.mirego.kmp.boilerplate"
    compileSdk = 33
    defaultConfig {
        minSdk = 21
        targetSdk = 33

        applicationId = "com.mirego.kmp.boilerplate"
        versionCode = 1
        versionName = "0.1"
    }
    buildTypes {
        debug {
            isDebuggable = true
            isMinifyEnabled = false
            isShrinkResources = false
        }
        release {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packagingOptions {
        // Do not include coroutines debug infrastructure in the resulting APK
        // See https://github.com/Kotlin/kotlinx.coroutines#avoiding-including-the-debug-infrastructure-in-the-resulting-apk
        resources.excludes += "DebugProbesKt.bin"
    }
}

dependencies {
    implementation(project(":shared"))

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.activity:activity-compose:1.6.1")

    // Jetpack Compose
    // See https://developer.android.com/jetpack/androidx/releases/compose#versions
    val composeBom = platform("androidx.compose:compose-bom:2023.01.00")
    implementation(composeBom)

    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.compose.material:material")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    // Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
}
