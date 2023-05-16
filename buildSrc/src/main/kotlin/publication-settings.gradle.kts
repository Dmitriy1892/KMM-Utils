import java.util.Base64
import java.util.Properties

plugins {
    `maven-publish`
    signing
}

// Stub secrets to let the project sync and build without the publication values set up
ext["signing.keyId"] = null
ext["signing.password"] = null
ext["signing.secretKeyRingFile"] = null
ext["ossrhUsername"] = null
ext["ossrhPassword"] = null

val secretPropsFile = project.rootProject.file("secret.properties")
if (secretPropsFile.exists()) {
    secretPropsFile.reader().use {
        Properties().apply {
            load(it)
        }
    }.onEach { (name, value) ->
        ext[name.toString()] = value
    }
} else {
    ext["signing.keyId"] = System.getenv("SIGNING_KEY_ID")
    ext["signing.password"] = System.getenv("SIGNING_PASSWORD")
    ext["signing.secretKeyRingFile"] = System.getenv("SIGNING_SECRET_KEY_RING_FILE")
    ext["ossrhUsername"] = System.getenv("OSSRH_USERNAME")
    ext["ossrhPassword"] = System.getenv("OSSRH_PASSWORD")
}

val javadocJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
}

fun getExtraString(name: String) = ext[name]?.toString()

publishing {
    repositories {
        maven {
            name = "sonatype"
            setUrl("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")

            credentials {
                username = getExtraString("ossrhUsername")
                password = getExtraString("ossrhPassword")
            }
        }
    }

    publications.withType<MavenPublication> {
        // Stub javadoc.jar artifact
//        artifact(javadocJar.get())

        pom {
            name.set("KMM-Utils")
            description.set("KMM-Utils is a library with useful classes and extensions for Kotlin Multiplatform Mobile development")
            url.set("https://github.com/Dmitriy1892/KMM-Utils")

            licenses {
                license {
                    name.set("Apache-2.0")
                    url.set("https://github.com/Dmitriy1892/KMM-Utils/LICENSE.md")
                }
            }

            developers {
                developer {
                    id.set("kodim1892")
                    name.set("Dmitriy Kotikov")
                    email.set("kodim1892@gmail.com")
                }
            }

            scm {
                url.set("https://github.com/Dmitriy1892/KMM-Utils")
            }
        }
    }
}

signing {
    sign(publishing.publications)
}