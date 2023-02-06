plugins {
    // Support convention plugins written in Kotlin.
    `kotlin-dsl`
    id("com.gevamu.plugins.plugin-common-conventions")
}

val plugin_version: String by project

group = "com.gevamu.plugins"
version = plugin_version

repositories {
    // Use the plugin portal to apply community plugins in convention plugins.
    gradlePluginPortal()
}

dependencies {
    implementation("com.gevamu.plugins:java-common-conventions:0.0.1")
}
