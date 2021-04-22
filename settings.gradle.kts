import de.fayard.refreshVersions.bootstrapRefreshVersions

buildscript {
    repositories { gradlePluginPortal() }
    dependencies.classpath("de.fayard.refreshVersions:refreshVersions:0.9.7")
}

bootstrapRefreshVersions()

enableFeaturePreview("VERSION_CATALOGS")

rootProject.name = "KWallet"
include(":app")
include(":data")
