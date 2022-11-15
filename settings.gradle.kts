pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Secret_Santa"
include(":androidApp")
include(":shared")
include(":common:core")
include(":common:app:api")
include(":common:app:data")
include(":common:app:presentation")
include(":common:umbrella-core")
include(":common:umbrella-ios")