import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.targets.native.tasks.KotlinNativeSimulatorTest

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    kotlin("plugin.serialization") version "1.7.20"
    id("com.android.library")
    id("org.jlleitschuh.gradle.ktlint") version "11.0.0"
}

version = "0.1"

kotlin {
    android {
        publishAllLibraryVariants()
    }
    ios()
    iosSimulatorArm64()

    cocoapods {
        summary = "Project summary"
        homepage = "https://github.com/mirego/your-project"
        name = "Shared"

        ios.deploymentTarget = "14.1"

        podfile = project.file("../ios/Podfile")

        framework {
            baseName = "Shared"
            isStatic = false
        }

        pod("SwiftLint")
    }

    @Suppress("UNUSED_VARIABLE")
    sourceSets {
        all {
            languageSettings {
                optIn("kotlin.Experimental")
                optIn("kotlinx.coroutines.ExperimentalCoroutinesApi")
            }
        }

        val coroutinesVersion = "1.6.4"

        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")
            }
        }
        val androidMain by getting
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }

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
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        compileSdk = 33
        minSdk = 21
        targetSdk = 33
    }
}

ktlint {
    android.set(true)
    enableExperimentalRules.set(true)
    filter {
        exclude { element -> element.file.path.contains("generated/") }
    }
}

// Make sure iOS simulator tests are ran on an available device (defaults to iPhone 14).
// Run `/usr/bin/xcrun simctl list devices available` to list the available devices on your machine
// See https://slack-chats.kotlinlang.org/t/535280/i-have-the-same-issue-leaving-a-comment-to-track
tasks.filterIsInstance<KotlinNativeSimulatorTest>().forEach { task ->
    task.deviceId = properties["iosSimulatorName"] as? String ?: "iPhone 14"
}

// Silence "source sets were configured but not added" warning
// See https://discuss.kotlinlang.org/t/disabling-androidandroidtestrelease-source-set-in-gradle-kotlin-dsl-script/21448/6
extensions.findByType<KotlinMultiplatformExtension>()?.let { ext ->
    val sourceSets = setOf(
        "androidAndroidTestRelease",
        "androidTestFixtures",
        "androidTestFixturesDebug",
        "androidTestFixturesRelease"
    )
    ext.sourceSets.removeAll { sourceSets.contains(it.name) }
}
