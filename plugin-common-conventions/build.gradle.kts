plugins {
    // Support convention plugins written in Kotlin.
    `kotlin-dsl`
    `maven-publish`
}

val plugin_version: String by project

group = "com.gevamu.plugins"
version = plugin_version

repositories {
    gradlePluginPortal()
    mavenCentral()
}

dependencies {
    implementation(gradlePlugin("com.gradle.plugin-publish", "1.0.0"))
    implementation(gradlePlugin("dev.gradleplugins.java-gradle-plugin", "1.6.9"))
    implementation("org.apache.maven.plugins:maven-plugin-plugin:3.7.1")
}

fun gradlePlugin(id: String, version: String) = "$id:$id.gradle.plugin:$version"

gradlePlugin {
    plugins {
        create("PluginConventions") {
            id = "com.gevamu.plugin-common-conventions"
            implementationClass = "${project.group}.PluginCommonConventionsPlugin"
            displayName = "Plugin for plugin conventions"
            description = "Custom plugin setup"
        }
    }
}
