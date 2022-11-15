plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    mavenLocal()
    google()
    maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

dependencies {
    implementation(Dependencies.Android.gradlePlugin)
    implementation(Dependencies.Kotlin.gradlePlugin)
    implementation(Dependencies.Kotlin.Serialization.gradlePlugin)
}

kotlin {
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
    sourceSets.getByName("main").kotlin.srcDir("buildSrc/src/main/kotlin")
}