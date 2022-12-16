plugins {
    id("multiplatform-setup")
    id("android-setup")
    kotlin("native.cocoapods")
}

version = "0.0.1"

kotlin {
    cocoapods {
        summary = "SecretSanta iOS SDK"
        homepage = "https://flagstudio.ru"
        ios.deploymentTarget = "14.0"

        framework {
            isStatic = false
            transitiveExport = false
            baseName = "SharedSDK"
            export(project(":common:core"))
            export(project(":common:umbrella-core"))
            export(project(":common:app:presentation"))
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":common:core"))
                implementation(project(":common:umbrella-core"))
                implementation(project(":common:app:presentation"))
            }
        }

        iosMain {
            dependencies {
                api(project(":common:core"))
                api(project(":common:umbrella-core"))
                api(project(":common:app:presentation"))
            }
        }
    }
}