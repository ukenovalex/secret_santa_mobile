plugins {
    id("android-setup")
    id("multiplatform-setup")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(project(":common:app:api"))
                api(project(":common:core"))
                implementation(Dependencies.MVI.core)
            }
        }
    }
}