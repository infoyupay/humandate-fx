plugins {
    `java-library`
    `maven-publish`
    signing
    id("org.openjfx.javafxplugin") version "0.1.0"
}

group = "com.infoyupay.humandate"
version = "0.1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21

    withSourcesJar()
    withJavadocJar()
}

javafx {
    // Keep minimal for now
    version = "21.0.9"
    modules = listOf("javafx.controls")
}

repositories {
    mavenCentral()
}

dependencies {
    api("com.infoyupay.humandate:humandate-core:1.0.0")

    // JavaFX deps vendrán después
    // testImplementation("org.testfx:testfx-junit5:...")

    testImplementation(platform("org.junit:junit-bom:6.0.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

    testImplementation("org.assertj:assertj-core:3.27.6")
}

tasks.test {
    useJUnitPlatform()
}
