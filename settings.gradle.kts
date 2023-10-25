@file:Suppress("UnstableApiUsage")

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://s3.amazonaws.com/mirego-maven/public")
        resolutionStrategy {
            eachPlugin {
                if (requested.id.namespace == "mirego") {
                    useModule("mirego:${requested.id.name}-plugin:${requested.version}")
                }
            }
        }
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://s3.amazonaws.com/mirego-maven/public")
    }
}

rootProject.name = "kmp-boilerplate"
include(":androidApp")
include(":shared")
