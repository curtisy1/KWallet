plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("org.jlleitschuh.gradle.ktlint") version "10.0.0"
    id("com.cmgapps.licenses") version "2.1.0"
}

android {
    compileSdkVersion(30)

    defaultConfig {
        applicationId = "eu.curtisy.kwallet"
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "0.0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = false
            setProguardFiles(
                listOf(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.AndroidX.Compose.version
    }

    packagingOptions {
        resources {
            excludes.addAll(
                listOf(
                    "META-INF/ktor-client-json.kotlin_module",
                    "META-INF/ktor-client-core.kotlin_module",
                    "META-INF/ktor-io.kotlin_module",
                    "META-INF/ktor-http.kotlin_module",
                    "META-INF/ktor-http-cio.kotlin_module",
                    "META-INF/ktor-utils.kotlin_module",
                    "META-INF/DEPENDENCIES"
                )
            )
        }
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(project(":data"))
    implementation(Dependencies.KotlinX.Coroutines.core)
    implementation(Dependencies.KotlinX.Coroutines.android)

    implementation(Dependencies.Kotlin.stdlib)
    implementation(Dependencies.AndroidX.core)
    implementation(Dependencies.AndroidX.appcompat)
    implementation(Dependencies.Android.material)
    implementation(Dependencies.AndroidX.startup)

    implementation(Dependencies.AndroidX.Compose.UI.ui)
    implementation(Dependencies.AndroidX.Compose.Material.material)
    implementation(Dependencies.AndroidX.Compose.UI.tooling)
    implementation(Dependencies.AndroidX.Activity.compose)
    implementation(Dependencies.AndroidX.Navigation.compose)
    implementation(Dependencies.AndroidX.Compose.Material.iconsCore)
    implementation(Dependencies.AndroidX.Compose.Material.iconsExtended)
    implementation(Dependencies.AndroidX.ConstraintLayout.compose)

    api(Dependencies.AndroidX.LifeCycle.runtime)
    api(Dependencies.AndroidX.LifeCycle.viewmodel)
    api(Dependencies.AndroidX.LifeCycle.extensions)
    api(Dependencies.AndroidX.LifeCycle.livedata)

    implementation(Dependencies.Accompanist.insets)
    implementation(Dependencies.Accompanist.flowLayout)

    implementation(Dependencies.Koin.core)
    implementation(Dependencies.Koin.coreExt)
    implementation(Dependencies.Koin.android)
    implementation(Dependencies.Koin.androidExt)
    implementation(Dependencies.Koin.workManager)
    implementation(Dependencies.Koin.compose)

    implementation(Dependencies.SqlDelight.runtime)
    implementation(Dependencies.SqlDelight.android)
    implementation(Dependencies.SqlDelight.coroutineExtensions)

    api(Dependencies.Timber.base)
}

ktlint {
    android.set(true)
    outputToConsole.set(true)
    outputColorName.set("RED")
    enableExperimentalRules.set(true)
}

licenses {
    additionalProjects.plus(":data")
    reports {
        val baseDir = "$buildDir/../../third-party-licenses/"
        markdown.enabled = true
        markdown.destination = file("$baseDir/third-party-licenses.md")
        html.enabled = true
        html.destination = file("$baseDir/third-party-licenses.html")
    }
}
