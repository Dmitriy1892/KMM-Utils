import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.ExtraPropertiesExtension
import org.gradle.kotlin.dsl.getByName
import org.gradle.kotlin.dsl.the
import java.util.Properties

val Project.projectJavaVersion: JavaVersion
    get() = JavaVersion.toVersion(libs.versions.java.get().toInt())

internal val Project.libs: LibrariesForLibs
    get() = the<LibrariesForLibs>()

fun Project.getMyLibraryVersion(): String {
    val propertiesFile = project.rootProject.file("version.properties")

    return propertiesFile.reader().use {
        val props = Properties().apply { load(it) }

        val versionMajor = props.getProperty("VERSION_MAJOR")
        val versionMinor = props.getProperty("VERSION_MINOR")
        val versionPatch = props.getProperty("VERSION_PATCH")

        "$versionMajor.$versionMinor.$versionPatch"
    }
}

fun Project.autoIncrementBuildVersionNumber() {
    val propertiesFile = project.rootProject.file("version.properties")
    propertiesFile.reader().use {
        val props = Properties().apply { load(it) }

        val versionPatch = props.getProperty("VERSION_PATCH").toInt() + 1
        props.setProperty("VERSION_PATCH", versionPatch.toString())
        props.store(propertiesFile.writer(), null)
    }
}

fun Project.getSecretProperties(): Properties {
    return try {
        val propertiesFile = project.rootProject.file("secret.properties")
        propertiesFile.reader().use { Properties().apply { load(it) } }
    } catch (e: Throwable) {
        Properties()
    }
}

fun Project.getExtraString(name: String): String? =
    extensions.getByName<ExtraPropertiesExtension>("ext").get(name)?.toString()