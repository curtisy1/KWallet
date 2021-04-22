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
        kotlinCompilerExtensionVersion = "1.0.0-beta02"
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
    implementation(libs.bundles.coroutines)

    implementation(libs.kotlin.stdlib)
    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.startup)

    implementation(libs.bundles.androidx.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.constraintlayout.compose)

    api(libs.bundles.androidx.lifecycle)

    implementation(libs.bundles.accompanist)

    implementation(libs.bundles.koin)

    implementation(libs.bundles.sqldelight)

    api(libs.timber)
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
