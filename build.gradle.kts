import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven(url = "https://www.jetbrains.com/intellij-repository/nightly")
    maven(url = "https://cache-redirector.jetbrains.com/intellij-dependencies")
    maven {
        url = uri("https://packages.jetbrains.team/maven/p/ij/intellij-shared-indexes-internal")
    }
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.jetbrains.intellij.ide:ide-starter:LATEST-TRUNK-SNAPSHOT")
    testImplementation("com.jetbrains.intellij.performanceTesting:performance-testing-commands:LATEST-TRUNK-SNAPSHOT")
    testImplementation("com.jetbrains.intellij.performanceTesting:performance-testing-maven-commands:LATEST-TRUNK-SNAPSHOT")
    testImplementation("com.jetbrains.intellij.performanceTesting:performance-testing-gradle-commands:LATEST-TRUNK-SNAPSHOT")
    implementation("junit:junit:4.13.2")
}


tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}