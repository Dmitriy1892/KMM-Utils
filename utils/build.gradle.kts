plugins {
    id("multiplatform-setup")
    kotlin("native.cocoapods")

    id("publication-settings")
    id("maven-publish")
}

group = "io.github.dmitriy1892.kmm"
version = getMyLibraryVersion()

kotlin {

    android {
        publishLibraryVariants("release", "debug")
    }

    cocoapods {
        summary = "Kotlin multiplatform utility classes and extensions"
        homepage = "https://github.com/Dmitriy1892/KMM-Utils"
        version = "1.0"
        ios.deploymentTarget = libs.versions.iosTargetVersion.get()
        name = "KmmUtils"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "KmmUtils"
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlinx.datetime)
                implementation(libs.kotlinx.coroutines.core)
            }
        }

        commonTest {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-annotations-common"))
            }
        }
    }
}

android {
    namespace = "io.github.dmitriy1892.kmm.utils"

    buildFeatures {
        buildConfig = true
    }
}