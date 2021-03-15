plugins {
    id("com.android.library")
    kotlin("android")
    id("com.squareup.sqldelight")
}

android {
    compileSdkVersion(30)

    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = false
            setProguardFiles(listOf(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"))
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(Dependencies.SqlDelight.runtime)
    implementation(Dependencies.SqlDelight.android)
    implementation(Dependencies.SqlDelight.coroutineExtensions)
}

sqldelight {
    database("WalletDatabase") {
        packageName = "eu.curtisy.kwallet.data"
        verifyMigrations = true
    }
}