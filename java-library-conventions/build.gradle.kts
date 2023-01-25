plugins {
    // Support convention plugins written in Kotlin.
    `kotlin-dsl`
    id("com.gevamu.plugins.plugin-common-conventions")
}

val plugin_version: String by project

group = "com.gevamu.plugins"
version = plugin_version

repositories {
    gradlePluginPortal()
}

dependencies {
//    implementation(gradlePlugin("com.gevamu.plugins.java-common-conventions", "0.0.1"))
    implementation("com.gevamu.plugins:java-common-conventions:0.0.1")
}
