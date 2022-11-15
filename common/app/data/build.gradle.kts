plugins {
    id("android-setup")
    id("multiplatform-setup")
}


kotlin {

    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":common:app:api"))
                implementation(project(":common:core"))
            }
        }
    }

}
