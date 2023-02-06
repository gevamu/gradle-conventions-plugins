val kotlin_version: String by project
val plugin_version: String by project

group = "com.gevamu.plugins"
version = plugin_version

plugins {
    id("com.gevamu.plugins.plugin-common-conventions")
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation(gradlePlugin("org.jetbrains.kotlin.jvm", kotlin_version))
    // Kotlin style checker (3.0.x is for Kotlin 1.4)
    implementation(gradlePlugin("org.jmailen.kotlinter", "3.0.2"))
    // Documentation engine for Kotlin
    implementation(gradlePlugin("org.jetbrains.dokka", kotlin_version))
}

configurations.all {
    resolutionStrategy.dependencySubstitution {
        substituteKotlinModule("org.jetbrains.kotlin:kotlin-stdlib-jdk7")
        substituteKotlinModule("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    }
}

gradlePlugin {
    plugins {
        create("kotlinCommonConventions")  {
            id = "${group}.kotlin-common-conventions"
            implementationClass = "${group}.KotlinCommonPlugin"
            displayName = "Kotlin Common Conventions"
            description = "Custom Kotlin setup for gevamu projects"
        }
    }
}

fun gradlePlugin(id: String, version: String) = "$id:$id.gradle.plugin:$version"

fun DependencySubstitutions.substituteKotlinModule(moduleNotation: String) {
    substitute(module(moduleNotation)).using(module("$moduleNotation:$kotlin_version"))
}
