tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

tasks.register("autoIncrementBuild") {
    group = "publishing"
    description = "Publishes all Maven publications to the external Maven repository."

    autoIncrementBuildVersionNumber()
}