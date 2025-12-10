plugins {
    id("java")
    id("application")
    id("org.openjfx.javafxplugin") version "0.1.0"
}

group = "com.infoyupay.humandate"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

javafx {
    // Keep minimal for now
    version = "21.0.9"
    modules = listOf("javafx.controls", "javafx.fxml")
}

application {
    mainClass = "com.infoyupay.humandate.fx.showcase.FxShowcaseApp"
    mainModule = "com.infoyupay.humandate.fx.showcase"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":"))
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}