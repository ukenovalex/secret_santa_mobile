plugins {
    id("multiplatform-setup")
    id("android-setup")
}

kotlin {

    sourceSets {
        commonMain {
            dependencies {
                implementation(Dependencies.Kodein.core)
                implementation(project(":common:core"))
                implementation(project(":common:app:data"))
            }
        }
    }
}
