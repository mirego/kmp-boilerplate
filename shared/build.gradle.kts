@file:Suppress("UNUSED_VARIABLE")

import co.touchlab.skie.configuration.EnumInterop
import co.touchlab.skie.configuration.FunctionInterop
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.native.cocoapods)
    alias(libs.plugins.serialization)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kspPlugin)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.mirego.kwordPlugin)
    alias(libs.plugins.apollo.graphql)
    alias(libs.plugins.skie.plugin)
}

version = "0.1"

val TRIKOT_FRAMEWORK_NAME = "Shared"

fun org.jetbrains.kotlin.gradle.plugin.mpp.Framework.configureFramework() {
    baseName = TRIKOT_FRAMEWORK_NAME
    isStatic = false
    export(libs.trikot.analytics)
    export(libs.trikot.kword)
    export(libs.trikot.datasources)
    export(libs.killswitch)
    export(libs.pilot.navigation)
    export(libs.pilot.viewmodel)
    export(libs.pilot.components)
    export(libs.kotlinx.coroutines.core)
    export(libs.kotlinx.serialization)
    binaryOption("bundleId", TRIKOT_FRAMEWORK_NAME)
}

kword {
    translationFile = file("src/commonMain/resources/translations/translation.en.json")
    enumClassName = "com.mirego.kmp.boilerplate.localization.KWordTranslation"
    generatedDir = file("src/commonMain/generated")
}

skie {
    analytics {
        disableUpload.set(true)
    }
    features {
        group("com.mirego.trikot") {
            EnumInterop.LegacyCaseName(true)
            FunctionInterop.LegacyName(true)
        }
    }
}

apollo {
    val file = File("src/commonMain/kotlin/com/mirego/kmp/boilerplate/graphql/schema.graphqls")
    packageName.set("com.mirego.kmp.boilerplate")
    srcDir("src/commonMain/kotlin/com/mirego/kmp/boilerplate/graphql")
    schemaFile.set(file)
    codegenModels.set("responseBased")

    introspection {
        endpointUrl.set("https://api.mirego.com/graphql")
        schemaFile.set(file)
    }
}

val updateGraphQLSchema: Task by tasks.creating {
    group = "apollo"
    dependsOn("downloadServiceApolloSchemaFromIntrospection")
}

kotlin {
    jvmToolchain(17)

    androidTarget {
        publishAllLibraryVariants()
    }

    iosX64()
    iosArm64()
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
                optIn("kotlin.Experimental")
                optIn("kotlin.time.ExperimentalTime")
                optIn("io.ktor.util.InternalAPI")
                optIn("kotlinx.serialization.InternalSerializationApi")
                optIn("kotlinx.serialization.ExperimentalSerializationApi")
                optIn("kotlinx.cinterop.ExperimentalForeignApi")
            }
        }

        val commonMain by getting {
            dependencies {
                implementation(libs.apollo.runtime)
                api(libs.koin.annotations)
                api(libs.kotlinx.coroutines.core)
                api(libs.kotlinx.serialization)
                api(libs.koin.core)
                implementation(libs.okio)
                implementation(libs.skie)
                api(libs.trikot.analytics)
                api(libs.trikot.datasources)
                api(libs.trikot.kword)
                api(libs.ktor.client.auth)
                api(libs.ktor.client.core)
                api(libs.ktor.client.contentNegotiation)
                api(libs.ktor.client.serialization)
                api(libs.killswitch)
                api(libs.pilot.viewmodel)
                api(libs.pilot.components)
                api(libs.pilot.navigation)
            }
            kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
            kotlin.srcDir(kword.generatedDir)
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.kotlinx.coroutines.test)
                implementation(libs.mockk.common)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.lifecycle.viewmodel)
                implementation(libs.androidx.lifecycle.viewmodel.ktx)
                implementation(libs.ktor.client.okHttp)
            }
        }

        val androidUnitTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
                implementation(libs.kotlin.test.junit)
                implementation(libs.mockk)
            }
        }

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting

        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                api(libs.ktor.client.darwin)
                implementation(libs.crashkios)
                api(libs.ktor.io)
                implementation(libs.kotlinx.serialization)
            }
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
}

ktlint {
    android.set(true)
    enableExperimentalRules.set(true)
    filter {
        exclude { element -> element.file.path.contains("generated/") }
        exclude { element -> element.file.path.contains("viewmodel/SharedImageResource") }
        exclude { element -> element.file.path.contains("analytics/Analytics") }
    }
}

tasks["runKtlintFormatOverCommonMainSourceSet"].dependsOn("kspCommonMainKotlinMetadata")
tasks["runKtlintCheckOverCommonMainSourceSet"].dependsOn("kspCommonMainKotlinMetadata")

val checkCommon: Task by tasks.creating {
    group = "verification"
    description = "Like check, but only with android target for common unit tests"
    dependsOn("ktlintCheck")
    dependsOn("testReleaseUnitTest")
}

tasks.withType<KotlinCompilationTask<*>>().configureEach {
    if (name != "kspCommonMainKotlinMetadata") {
        dependsOn("kspCommonMainKotlinMetadata")
    } else {
        dependsOn(tasks.withType<com.mirego.kword.KWordEnumGenerate>())
    }
}

tasks["runKtlintFormatOverCommonMainSourceSet"].dependsOn("kspCommonMainKotlinMetadata")
tasks["runKtlintCheckOverCommonMainSourceSet"].dependsOn("kspCommonMainKotlinMetadata")
