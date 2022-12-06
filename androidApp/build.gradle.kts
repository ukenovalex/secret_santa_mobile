plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "ru.flagstudio.secretsanta.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "ru.flagstudio.secretsanta.android"
        minSdk = 23
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
        dataBinding = true
        viewBinding = true
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":common:core"))
    implementation(project(":common:app:presentation"))
    implementation(project(":common:umbrella-core"))
    implementation(Dependencies.Android.Compose.ui)
    implementation(Dependencies.Android.Compose.tooling)
    implementation(Dependencies.Android.Compose.tooling_preview)
    implementation(Dependencies.Android.Compose.fondation)
    implementation(Dependencies.Android.Compose.material)
    implementation(Dependencies.Android.Compose.animation)
    implementation(Dependencies.Android.Compose.activity_compose)
    implementation(Dependencies.Android.Compose.viewModel)
    implementation(Dependencies.Android.Compose.Navigation.core)

    implementation(Dependencies.Android.ExoPlayer.core)
    implementation(Dependencies.Android.ExoPlayer.ui)

    implementation(Dependencies.MVI.core)
    implementation(Dependencies.MVI.compose)

    implementation("com.google.android.material:material:1.7.0")
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
}