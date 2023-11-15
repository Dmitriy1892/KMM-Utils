plugins {
    id("android-application-setup")
}

kotlin {
    sourceSets {
        androidMain {
            dependencies {
                implementation(project(":utils"))

                implementation(libs.bundles.androidApp)
            }
        }
    }
}

android {
    namespace = "com.coldfier.kmm.utils.android"

    defaultConfig {
        applicationId = "com.coldfier.kmm.utils.android"

        versionCode = 1
        versionName = "1"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.android.compose.compiler.get()
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}