rootProject.name = "gradle-conventions-plugins"

pluginManagement {
    repositories {
        // Use the plugin portal to apply community plugins in convention plugins.
        gradlePluginPortal()
    }
    includeBuild("kotlin-common-conventions")
    includeBuild("plugin-common-conventions")
    includeBuild("java-common-conventions")
    includeBuild("java-library-conventions")
    includeBuild("cordformation-conventions")
    includeBuild("kotlin-cordapp-conventions")
    includeBuild("publish-cordapp-conventions")
}
