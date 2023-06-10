import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

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
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "17"
}