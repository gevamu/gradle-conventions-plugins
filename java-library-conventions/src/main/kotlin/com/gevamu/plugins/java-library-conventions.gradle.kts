package com.gevamu.plugins

import org.gradle.kotlin.dsl.`java-library`

plugins {
    // Include Java Common Conventions
    id("com.gevamu.plugins.java-common-conventions")

    // Apply the java-library plugin for API and implementation separation.
    `java-library`
}

repositories {
    // Use the plugin portal to apply community plugins in convention plugins.
    gradlePluginPortal()
}

java {
    withJavadocJar()
    withSourcesJar()
}
