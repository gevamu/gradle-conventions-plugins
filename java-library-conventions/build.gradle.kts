plugins {
    // Support convention plugins written in Kotlin.
    `kotlin-dsl`
    id("com.gevamu.plugins.plugin-common-conventions")
}

group = "com.gevamu.plugins"
version = "0.0.1"

repositories {
    gradlePluginPortal()
}

dependencies {
    implementation(gradlePlugin("com.gevamu.plugins.java-common-conventions", "0.0.1"))
}

fun gradlePlugin(id: String, version: String) = "$id:$id.gradle.plugin:$version"
