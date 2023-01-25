rootProject.name = "cordformation-conventions"

pluginManagement {
    repositories {
        // Use the plugin portal to apply community plugins in convention plugins.
        gradlePluginPortal()
        maven("https://software.r3.com/artifactory/corda")
    }

    includeBuild("../plugin-common-conventions")
}
