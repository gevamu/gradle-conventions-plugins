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

package com.gevamu.plugins

val vcsUrl: String = "https://github.com/gevamu/gradle-conventions-plugins/"
val pluginsGroup: String = "com.gevamu.plugins"

project.group = pluginsGroup

plugins {
    `maven-publish`
    `java-gradle-plugin`
    java
    id("com.gradle.plugin-publish")
}

tasks.withType<Jar> {
    manifest {
        //FIXME("Version isn't settled in jar manifest, only in sources and javadoc")
        attributes["Created-By"] = "${System.getProperty("java.version")} (${System.getProperty("java.vendor")})"
        attributes["Specification-Title"] = ""
        attributes["Specification-Vendor"] = "Exactpro Systems LLC"
        attributes["Implementation-Title"] = project.name
        attributes["Implementation-Vendor"] = "Exactpro Systems LLC"
        attributes["Implementation-Vendor-Id"] = "com.exactpro"
        attributes["Implementation-Version"] = project.version
        attributes["Implementation-License"] = "Apache License Version 2.0"
    }
}

//fun applyJava(project: Project) {
//    project.pluginManager.apply(JavaPlugin::class.java)
//
//    val javaPlugin = extensions.getByType(JavaPlugin::class.java)
//
//    javaPlugin.add
//}


//pluginManager.withPlugin("java") {
//    from(components["java"])
//    artifact(tasks.register("sourcesJar", org.gradle.jvm.tasks.Jar::class) {
//        dependsOn(tasks["classes"])
//        archiveClassifier.set("sources")
//        from(sourceSets.main.get().allSource)
//    })
//}

gradlePlugin {
    plugins {
        create("pluginCommonConventions")  {
            id = "${project.group}.plugin-common-conventions"
            implementationClass = "${project.group}.PluginCommonConventionsPlugin"
            displayName = "Plugin Common Conventions"
            description = "Plugin for custom configuration of gevamu related apps"
        }
    }
}

pluginBundle {
    website = vcsUrl
    vcsUrl = vcsUrl
    tags = listOf("gevamu")
}

publishing {
    publications {
        val found = this.filterIsInstance<MavenPublication>()
        found.forEach {
            it.setupPom()
        }
//        create<MavenPublication>(project.name) {
//            groupId = project.group.toString()
//            artifactId = project.name
//            artifact(project.tasks["sourcesJar"])
//            this.setupPom()
//        }
    }
    repositories {
//        maven {
//            name = "GitHubPackages"
//            setUrl("https://maven.pkg.github.com/gevamu/plugin-common-conventions")
//            credentials {
//                username = System.getenv("GITHUB_ACTOR")
//                password = System.getenv("GITHUB_TOKEN")
//            }
//        }
        repositories {
            maven {
                name = "test"
                setUrl("file://" + System.getenv("HOME") + "/.m2/repository/")
            }
        }
    }
}

repositories {
    gradlePluginPortal()
    mavenCentral()
}

fun MavenPublication.setupPom() {
    pom {
        name.set("Gevamu plugin - ${project.name}")
        description.set("Plugin for custom configuration of gevamu related apps")
        url.set(vcsUrl)
        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }
        scm {
            connection.set("scm:git:$vcsUrl.git")
            developerConnection.set("scm:git:$vcsUrl.git")
            url.set("$vcsUrl.git")
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
