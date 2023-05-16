plugins {
    id("multiplatform-setup")
    kotlin("native.cocoapods")

    id("publication-settings")
    id("maven-publish")
}

group = "io.github.dmitriy1892.kmm.utils"
version = libs.versions.kmm.utils.get()

kotlin {

    android {
        publishLibraryVariants("release", "debug")
    }

    cocoapods {
        summary = "Kotlin multiplatform utility classes and extensions"
        homepage = "https://github.com/Dmitriy1892/KMM-Utils"
        version = "1.0"
        ios.deploymentTarget = "14.1"
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

        androidMain {
            dependencies {
                implementation(libs.androidx.lifecycle.viewmodel)
                implementation(libs.androidx.lifecycle.compose.viewmodel)
            }
        }
    }
}

android {
    namespace = "com.coldfier.kmm.utils"
}