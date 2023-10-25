@file:Suppress("UNUSED_VARIABLE")

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.native.cocoapods)
    alias(libs.plugins.serialization)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kspPlugin)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.mirego.kwordPlugin)
}

version = "0.1"

val TRIKOT_FRAMEWORK_NAME = "Shared"

fun org.jetbrains.kotlin.gradle.plugin.mpp.Framework.configureFramework() {
    baseName = TRIKOT_FRAMEWORK_NAME
    isStatic = false
    export(libs.trikot.vmd)
    export(libs.trikot.kword)
    export(libs.trikot.datasources)
    export(libs.trikot.vmd.annotations)
    binaryOption("bundleId", TRIKOT_FRAMEWORK_NAME)
}

kword {
    translationFile = file("src/commonMain/resources/translations/translation.en.json")
    enumClassName = "com.mirego.kmp.boilerplate.localization.KWordTranslation"
    generatedDir = file("src/commonMain/generated")
}

kotlin {
    jvmToolchain(17)

    androidTarget {
        publishAllLibraryVariants()
    }

    ios()
    iosSimulatorArm64()

    cocoapods {
        name = TRIKOT_FRAMEWORK_NAME
        version = "0.1"
        summary = TRIKOT_FRAMEWORK_NAME
        homepage = "https://github.com/mirego/your-project"
        license = "BSD-3"
        extraSpecAttributes = mutableMapOf(
            "resources" to "\"src/commonMain/resources/translations/*\"",
            "prepare_command" to """
                <<-CMD
                    ../gradlew :shared:generateDummyFramework
                CMD
            """.trimIndent()
        )

        framework {
            configureFramework()
        }
    }

    sourceSets {
        all {
            languageSettings {
                optIn("kotlinx.coroutines.ExperimentalCoroutinesApi")
            }
        }

        val commonMain by getting {
            dependencies {
                api(libs.trikot.vmd)
                api(libs.trikot.kword)
                api(libs.trikot.datasources)
                api(libs.trikot.vmd.annotations)
                api(libs.kotlinx.coroutines.core)
                api(libs.kotlinx.serialization.json)
                api(libs.koin.core)
                api(libs.koin.annotations)
            }
            kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
            kotlin.srcDir(kword.generatedDir)
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.kotlinx.coroutines.test)
            }
        }
        val androidMain by getting
        val androidUnitTest by getting

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by getting {
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by getting {
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "com.mirego.kmp.boilerplate.common"
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

    compileSdk = 34
    defaultConfig {
        minSdk = 28
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    add("kspCommonMainMetadata", libs.ksp.koinCompiler)
    add("kspCommonMainMetadata", libs.trikot.vmd.annotations.compiler)
}

ktlint {
    android.set(true)
    enableExperimentalRules.set(true)
    filter {
        exclude { element -> element.file.path.contains("generated/") }
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.dsl.KotlinCompile<*>>().all {
    if (name != "kspCommonMainKotlinMetadata") {
        dependsOn("kspCommonMainKotlinMetadata")
    } else {
        dependsOn(tasks.withType<com.mirego.kword.KWordEnumGenerate>())
    }
}
