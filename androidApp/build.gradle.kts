plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    defaultConfig {
        compileSdk = 32
        minSdk = 21
        targetSdk = 32

        applicationId = "com.mirego.kmp.boilerplate.android"
        versionCode = 1
        versionName = "0.1"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0-beta03"
    }
    packagingOptions {
        // Do not include coroutines debug infrastructure in the resulting APK
        // See https://github.com/Kotlin/kotlinx.coroutines#avoiding-including-the-debug-infrastructure-in-the-resulting-apk
        resources.excludes += "DebugProbesKt.bin"
    }
}

dependencies {
    implementation(project(":shared"))

    implementation("androidx.appcompat:appcompat:1.4.2")
    implementation("androidx.activity:activity-compose:1.4.0")

    val composeVersion = "1.2.0-beta03"
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling:$composeVersion")
    implementation("androidx.compose.material:material:$composeVersion")

    val coroutinesVersion = "1.6.2"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")
}
