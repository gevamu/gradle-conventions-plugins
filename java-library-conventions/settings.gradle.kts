rootProject.name = "java-library-conventions"


pluginManagement {
    repositories {
        // Use the plugin portal to apply community plugins in convention plugins.
        gradlePluginPortal()
    }

    includeBuild("../plugin-common-conventions")
}

includeBuild("../java-common-conventions")
