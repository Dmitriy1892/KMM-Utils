import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.util.Base64

plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.gradleplugins.kotlin)
    implementation(libs.gradleplugins.compose)
    implementation(libs.gradleplugins.android)
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "11"
}