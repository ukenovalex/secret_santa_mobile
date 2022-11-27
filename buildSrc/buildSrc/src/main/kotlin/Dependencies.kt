object Dependencies {

    object KVault {
        private const val version = "1.10.0"
        const val core = "com.liftric:kvault:$version"
    }

    object MVI {
        private const val version = "0.11"
        const val core = "com.adeo:kviewmodel:$version"
        const val compose = "com.adeo:kviewmodel-compose:$version"
    }

    object Compose {
        private const val version = "1.2.0-beta01"
        const val gradlePlugin = "org.jetbrains.compose:compose-gradle-plugin:$version"
    }

    object Kodein {
        const val core = "org.kodein.di:kodein-di:7.1.0"
    }

    object Kotlin {
        private const val version = "1.7.10"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"

        object Serialization {
            const val gradlePlugin = "org.jetbrains.kotlin:kotlin-serialization:1.6.21"
            const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-core:1.3.0"
        }

        object Coroutines {
            private const val version = "1.6.4"
            const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        }
    }
    object Android {
        const val gradlePlugin = "com.android.tools.build:gradle:7.2.2"

        object Compose {
            private const val version = "1.3.1"
            private const val version_activity = "1.6.1"
            const val ui = "androidx.compose.ui:ui:$version"
            const val tooling = "androidx.compose.ui:ui-tooling:$version"
            const val tooling_preview = "androidx.compose.ui:ui-tooling-preview:$version"
            const val fondation = "androidx.compose.foundation:foundation:$version"
            const val material = "androidx.compose.material:material:$version"
            const val activity_compose = "androidx.activity:activity-compose:$version_activity"
            const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1"

            object Navigation {
                private const val version = "2.5.3"
                const val core = "androidx.navigation:navigation-compose:$version"
            }
        }
    }
    object Ktor {
        private const val version = "2.1.0"
        const val core = "io.ktor:ktor-client-core:$version"
        const val json = "io.ktor:ktor-client-json:$version"
        const val ios = "io.ktor:ktor-client-ios:$version"
        const val negotiation = "io.ktor:ktor-client-content-negotiation:$version"
        const val kotlin_json = "io.ktor:ktor-serialization-kotlinx-json:$version"
        const val serialization = "io.ktor:ktor-client-serialization:$version"
        const val logging = "io.ktor:ktor-client-logging:$version"
        const val android = "io.ktor:ktor-client-android:$version"
        const val auth = "io.ktor:ktor-client-auth:$version"
    }
}