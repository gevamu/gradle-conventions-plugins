/*
 * Copyright 2023 Exactpro Systems Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

val kotlin_version: String by project

plugins {
    id("java-gradle-plugin")
    id("maven-publish")
    id("com.gradle.plugin-publish") version "0.18.0"
}

repositories {
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

fun gradlePlugin(id: String, version: String) = "$id:$id.gradle.plugin:$version"

fun DependencySubstitutions.substituteKotlinModule(moduleNotation: String) {
    substitute(module(moduleNotation)).using(module("$moduleNotation:$kotlin_version"))
}
