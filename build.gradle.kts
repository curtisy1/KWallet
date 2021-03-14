// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Dependencies.Android.gradle)
        classpath(kotlin("gradle-plugin", version = Dependencies.Kotlin.version))
        classpath(Dependencies.SqlDelight.gradle)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter() {
            content {
                includeModule("org.jetbrains.kotlinx", "kotlinx-collections-immutable-jvm")
            }
        }
    }
}

tasks.register<Delete>("clean").configure {
    delete(rootProject.buildDir)
 }