rootProject.name = "kotlin-cordapp-conventions"


pluginManagement {
    repositories {
        // Use the plugin portal to apply community plugins in convention plugins.
        gradlePluginPortal()
    }

    includeBuild("../plugin-common-conventions")
}

includeBuild("../java-library-conventions")
