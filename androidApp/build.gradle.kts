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
        kotlinCompilerExtensionVersion = "1.3.0"
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
    implementation(project(":shared"))
    implementation(project(":common:core"))
    implementation(project(":common:app:presentation"))
    implementation(project(":common:umbrella-core"))
    implementation(Dependencies.Android.Compose.ui)
    implementation(Dependencies.Android.Compose.tooling)
    implementation(Dependencies.Android.Compose.tooling_preview)
    implementation(Dependencies.Android.Compose.fondation)
    implementation(Dependencies.Android.Compose.material)
    implementation(Dependencies.Android.Compose.activity_compose)
    implementation(Dependencies.Android.Compose.viewModel)

    implementation(Dependencies.MokoMVVM.Android.databinding)
    implementation(Dependencies.MokoMVVM.Android.flow_compose)
    implementation(Dependencies.MokoMVVM.Android.livedata_compose)
    implementation(Dependencies.MokoMVVM.Android.livedata_glide)
    implementation(Dependencies.MokoMVVM.Android.livedata_material)
    implementation(Dependencies.MokoMVVM.Android.livedata_swiperefresh)
    implementation(Dependencies.MokoMVVM.Android.viewbinding)

    implementation("com.google.android.material:material:1.7.0")
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
}