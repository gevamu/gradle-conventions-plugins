plugins {
    // Support convention plugins written in Kotlin.
    `kotlin-dsl`
    java
    id("com.gevamu.plugins.plugin-common-conventions")
}

group = "com.gevamu.plugins"
version = "0.0.1"

repositories {
    // Use the plugin portal to apply community plugins in convention plugins.
    gradlePluginPortal()
}

