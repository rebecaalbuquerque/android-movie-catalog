import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    jcenter()
    google()
}

dependencies {
    implementation(kotlin("gradle-plugin", "1.4.10"))
    implementation("com.android.tools.build:gradle:4.1.0")
    implementation("androidx.navigation:navigation-safe-args-gradle-plugin:2.3.0")
}