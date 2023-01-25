plugins {
    // Support convention plugins written in Kotlin.
    `kotlin-dsl`
    id("com.gevamu.plugins.plugin-common-conventions") version("0.0.1")
}

group = "com.gevamu.plugins"
version = "0.0.1"

repositories {
    gradlePluginPortal()
}

dependencies {
//    implementation(gradlePlugin("com.gevamu.plugins.java-common-conventions", "0.0.1"))
    implementation("com.gevamu.plugins:java-common-conventions:0.0.1")
}
