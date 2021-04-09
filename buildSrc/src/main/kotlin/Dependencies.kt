object Dependencies {

    object Accompanist {
        private const val version = "0.7.0"
        private const val path = "com.google.accompanist"

        const val insets = "$path:accompanist-insets:$version"
        const val flowLayout = "$path:accompanist-flowlayout:$version"
    }

    object Android {
        const val gradle = "com.android.tools.build:gradle:7.0.0-alpha13"
        const val material = "com.google.android.material:material:1.3.0"
    }

    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:1.3.0-beta01"
        const val core = "androidx.core:core-ktx:1.5.0-beta03"
        const val startup = "androidx.startup:startup-runtime:1.0.0"

        object Activity {
            private const val version = "1.3.0-alpha04"
            const val compose = "androidx.activity:activity-compose:$version"
        }

        object Compose {
            const val version = "1.0.0-beta02"
            private const val basePath = "androidx.compose"

            object Material {
                private const val path = "$basePath.material"

                const val iconsCore = "$path:material-icons-core:$version"
                const val iconsExtended = "$path:material-icons-extended:$version"
                const val material = "$path:material:$version"
            }

            object UI {
                private const val path = "$basePath.ui"

                const val ui = "$path:ui:$version"
                const val tooling = "$path:ui-tooling:$version"
            }
        }

        object ConstraintLayout {
            private const val version = "1.0.0-alpha04"
            const val compose = "androidx.constraintlayout:constraintlayout-compose:$version"
        }

        object LifeCycle {
            private const val version = "2.3.0"
            private const val path = "androidx.lifecycle"

            const val extensions = "$path:lifecycle-extensions:2.2.0" // TODO: Deprecated.. Remove once koin runs without it
            const val livedata = "$path:lifecycle-livedata-ktx:$version"
            const val runtime = "$path:lifecycle-runtime-ktx:$version"
            const val viewmodel = "$path:lifecycle-viewmodel-ktx:$version"
        }

        object Navigation {
            private const val version = "1.0.0-alpha09"
            const val compose = "androidx.navigation:navigation-compose:$version"
        }
    }

    object Kotlin {
        private const val version = "1.4.31"
        private const val path = "org.jetbrains.kotlin"

        const val stdlib = "$path:kotlin-stdlib-jdk8:$version"
        const val gradle = "$path:kotlin-gradle-plugin:$version"
    }

    object KotlinX {
        object Coroutines {
            private const val version = "1.4.2"
            private const val path = "org.jetbrains.kotlinx"

            const val android = "$path:kotlinx-coroutines-android:$version"
            const val core = "$path:kotlinx-coroutines-core:$version"
        }
    }

    object Ktor {
        private const val version = "1.4.2"
        private const val path = "io.ktor"

        const val apache = "$path:ktor-client-apache:$version"
        const val basicAuth = "$path:ktor-client-auth-basic:$version"
        const val gson = "$path:ktor-client-gson:$version"
        const val json = "$path:ktor-client-json:$version"
        const val jvmAuth = "$path:ktor-client-auth-jvm:$version"
        const val logging = "$path:ktor-client-logging-jvm:$version"
        const val okhttp = "$path:ktor-client-okhttp:$version"
    }

    object Koin {
        private const val version = "3.0.1-beta-1"
        private const val path = "io.insert-koin"

        const val android = "$path:koin-android:$version"
        const val androidExt = "$path:koin-android-ext:$version"
        const val compose = "$path:koin-androidx-compose:$version"
        const val core = "$path:koin-core:$version"
        const val coreExt = "$path:koin-core-ext:$version"
        const val workManager = "$path:koin-androidx-workmanager:$version"
    }

    object SqlDelight {
        private const val version = "1.4.4"
        const val android = "com.squareup.sqldelight:android-driver:$version"
        const val coroutineExtensions = "com.squareup.sqldelight:coroutines-extensions:$version"
        const val gradle = "com.squareup.sqldelight:gradle-plugin:$version"
        const val runtime = "com.squareup.sqldelight:runtime:$version"
    }

    object Timber {
        private const val version = "4.7.1"
        const val base = "com.jakewharton.timber:timber:$version"
    }
}