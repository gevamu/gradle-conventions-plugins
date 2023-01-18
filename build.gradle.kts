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
val release_version: String by project
val java_target_version: String by project
val vcs_url: String by project


plugins {
    id("maven-publish")
    id("java-gradle-plugin")
    id("com.gradle.plugin-publish") version "0.18.0"
    id("org.jetbrains.kotlin.jvm") version "1.4.32"
}

repositories {
    gradlePluginPortal()
    mavenCentral()
}

subprojects {
    apply {
        plugin("maven-publish")
        plugin("java-gradle-plugin")
        plugin("com.gradle.plugin-publish")
    }

    this@subprojects.version = release_version
    this@subprojects.group = "com.gevamu.plugins"

    evaluationDependsOn(this.path)

    pluginManager.withPlugin("java") {
        java {
            toolchain {
                languageVersion.set(JavaLanguageVersion.of(java_target_version))
            }
        }

        tasks.withType<Jar> {
            manifest {
                attributes["Created-By"] = "${System.getProperty("java.version")} (${System.getProperty("java.vendor")})"
                attributes["Specification-Title"] = ""
                attributes["Specification-Vendor"] = "Exactpro Systems LLC"
                attributes["Implementation-Title"] = this@subprojects.name
                attributes["Implementation-Vendor"] = "Exactpro Systems LLC"
                attributes["Implementation-Vendor-Id"] = "com.exactpro"
                attributes["Implementation-Version"] = this@subprojects.version
                attributes["Implementation-License"] = "Apache License Version 2.0"
            }
        }

        pluginManager.withPlugin("java-gradle-plugin") {
            pluginBundle {
                website = vcs_url
                vcsUrl = vcs_url
                tags = listOf("gevamu")

                mavenCoordinates {
                    groupId = this@subprojects.group as String
                    artifactId = this@subprojects.name
                }
            }
        }
    }

    pluginManager.withPlugin("org.jetbrains.kotlin.jvm") {
        kotlin {
            java {
                toolchain {
                    languageVersion.set(JavaLanguageVersion.of(java_target_version))
                }
            }
        }

        tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
            kotlinOptions {
                apiVersion = "1.4"
                languageVersion = "1.4"
                freeCompilerArgs = listOf("-Xjvm-default=all")
            }
        }
    }

    tasks.withType<ValidatePlugins>().configureEach {
        // Ask Gradle to tell us how to annotate tasks correctly.
        enableStricterValidation.set(true)
    }

    tasks.withType<GenerateModuleMetadata>().configureEach {
        enabled = false
    }
}

configure(subprojects) {
    publishing {
        publications {
            configureEach {
                if (this is MavenPublication) {
                    pom {
                        this.name.set(this@configure.name)
                        this.description.set(this@configure.description)
                        this.url.set(vcs_url)
                        licenses {
                            license {
                                name.set("The Apache License, Version 2.0")
                                url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                            }
                        }
                        scm {
                            connection.set("scm:git:${vcs_url}.git")
                            developerConnection.set("scm:git:${vcs_url}.git")
                            url.set("${vcs_url}.git")
                        }
                        developers {
                            developer {
                                name.set("Gevamu")
                                organization.set("Exactpro Systems Limited")
                                organizationUrl.set("https://gevamu.com/")
                            }
                        }
                    }
                }
            }
            register(name, MavenPublication::class) {
                groupId = this@configure.group as String
                artifactId = this@configure.name
                pluginManager.withPlugin("java") {
                    from(components["java"])
                    artifact(tasks.register("sourcesJar", org.gradle.jvm.tasks.Jar::class) {
                        dependsOn(tasks["classes"])
                        archiveClassifier.set("sources")
                        from(sourceSets.main.get().allSource)
                    })
                }

                pom {
                    name.set(this@configure.name)
                    description.set(this@configure.description)
                }
            }
        }

        repositories {
            maven {
                name = "test"
                setUrl("file://" + System.getenv("HOME") + "/.m2/repository/")
            }
        }
    }
}
