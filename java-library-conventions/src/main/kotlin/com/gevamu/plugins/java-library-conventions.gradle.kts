package com.gevamu.plugins

import gradle.kotlin.dsl.accessors._64acc05bf1a66f2c855e386526b4bcff.java
import org.gradle.kotlin.dsl.`java-library`

plugins {
    // Include Java Common Conventions
//    id("com.gevamu.plugins.java-common-conventions")

    // Apply the java-library plugin for API and implementation separation.
    `java-library`
}

java {
    withJavadocJar()
    withSourcesJar()
}
