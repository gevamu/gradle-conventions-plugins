val projects = listOf(
    "plugin-common-conventions",
    "java-common-conventions",
    "java-library-conventions",
    "kotlin-common-conventions"
)


listOf("build", "clean", "assemble", "check", "publish").forEach { taskName ->
    tasks.register(taskName) {
        projects.forEach { projectName ->
            dependsOn(gradle.includedBuild(projectName).task(":$taskName"))
        }
        this.group = "build"
    }
}
