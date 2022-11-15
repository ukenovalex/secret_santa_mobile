plugins {
    id("android-setup")
    id("multiplatform-setup")
    kotlin("plugin.serialization")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(Dependencies.Kotlin.Serialization.serialization)
            }
        }
    }
}
