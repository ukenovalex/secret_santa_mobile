plugins {
    id("com.android.library")
    kotlin("multiplatform")
    kotlin("kapt")
}

kotlin {
    android()
    ios()
    iosX64()
    iosArm64()



    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }
}