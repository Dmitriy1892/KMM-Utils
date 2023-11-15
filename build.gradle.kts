tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory.asFile.get())
}

tasks.register("autoIncrementBuild") {
    group = "publishing"
    description = "Publishes all Maven publications to the external Maven repository."

    autoIncrementBuildVersionNumber()
}