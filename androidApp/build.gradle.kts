plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.crashlyticsPlugin)
}

kotlin {
    jvmToolchain(17)
}

android {
    namespace = "com.mirego.kmp.boilerplate"
    compileSdk = 34
    defaultConfig {
        minSdk = 28
        targetSdk = 34

        applicationId = "com.mirego.kmp.boilerplate"
        versionCode = 1
        versionName = "0.1"

        val translationFilePath = "${project.rootDir}/shared/src/commonMain/resources/translations/translation.en.json"
        buildConfigField("String", "KWORD_TRANSLATION_FILE_PATH", "\"$translationFilePath\"")
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
    androidResources {
        generateLocaleConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidComposeCompiler.get()
    }
    packaging {
        // Do not include coroutines debug infrastructure in the resulting APK
        // See https://github.com/Kotlin/kotlinx.coroutines#avoiding-including-the-debug-infrastructure-in-the-resulting-apk
        resources.excludes += "DebugProbesKt.bin"
    }

    sourceSets {
        getByName("main") {
            resources.srcDirs("../shared/src/commonMain/resources")
        }
    }

    lint {
        lintConfig = file("$rootDir/androidApp/android_picasso_lint.xml")
    }

    flavorDimensions += "environment"

    productFlavors {
        create("ci") {
            dimension = "environment"
            applicationIdSuffix = ".ci"
        }

        create("store") {
            dimension = "environment"
        }
    }
}

dependencies {
    implementation(project(":shared"))

    implementation(libs.android.splash)
    implementation(libs.android.firebase.analytics)
    implementation(libs.android.firebase.crashlytics)
    implementation(platform(libs.android.firebase.bom))
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.accompanist.placeholder.material)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.material)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
    implementation(libs.koin.androidx.compose.navigation)
    implementation(libs.trikot.viewmodels.databinding)
    implementation(libs.trikot.vmd.compose)
    "ciImplementation"(libs.appcenter)
    "storeImplementation"(libs.appcenter.play)
}
