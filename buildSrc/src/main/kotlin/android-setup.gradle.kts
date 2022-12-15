import org.jetbrains.kotlin.konan.properties.loadProperties

plugins {
    id("com.android.library")
}

android {
    compileSdk = 33

    defaultConfig {
        minSdk = 21
        targetSdk = 33
    }

    buildFeatures {
        compose = true
    }

    signingConfigs {
        create("release") {
            try {
                val keystoreProperties = loadProperties("keystore.properties")
                storeFile = file(keystoreProperties["SANTA_UPLOAD_STORE_FILE"] as String)
                storePassword = keystoreProperties["SANTA_UPLOAD_STORE_PASSWORD"] as String
                keyAlias = keystoreProperties["SANTA_UPLOAD_KEY_ALIAS"] as String
                keyPassword = keystoreProperties["SANTA_UPLOAD_KEY_PASSWORD"] as String
            } catch (_: Exception) {}
        }
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    sourceSets {
        named("main") {
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
            res.srcDirs("src/androidMain/res", "src/commonMain/resources")
        }
    }
}
