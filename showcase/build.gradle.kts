import org.gradle.internal.os.OperatingSystem
import org.gradle.api.tasks.bundling.Zip

plugins {
    id("java")
    id("application")
    id("org.openjfx.javafxplugin") version "0.1.0"
}

group = "com.infoyupay.humandate"
version = "1.0.0"

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

tasks.register<Exec>("jlinkShowcase") {
    group = "distribution"
    description = "Builds a custom runtime image for the humandate-fx Showcase using jlink"

    dependsOn(tasks.named("jar"))

    val imageDir = layout.buildDirectory.dir("jlink/humandate-fx-showcase")
    val jarFile = tasks.named<Jar>("jar").flatMap { it.archiveFile }

    inputs.files(
        configurations.runtimeClasspath,
        jarFile
    )

    outputs.dir(imageDir)

    doFirst {
        delete(imageDir)
    }

    commandLine(
        "${System.getProperty("java.home")}/bin/jlink",
        "--module-path",
        configurations.runtimeClasspath.get().asPath + File.pathSeparator + jarFile.get().asFile.absolutePath,
        "--add-modules", application.mainModule.get(),
        "--launcher",
        "humandate-fx-showcase=${application.mainModule.get()}/${application.mainClass.get()}",
        "--output", imageDir.get().asFile.absolutePath,
        "--strip-debug",
        "--no-man-pages",
        "--no-header-files",
        "--compress=zip-2"
    )
}


        tasks.register<Zip>("zipShowcaseRuntime") {
            group = "distribution"
            description = "Zips the jlink runtime for the humandate-fx Showcase"

            dependsOn("jlinkShowcase")

            val os = when {
                OperatingSystem.current().isWindows -> "windows"
                OperatingSystem.current().isMacOsX -> "macos"
                else -> "linux"
            }

            val arch = System.getProperty("os.arch").let {
                when (it) {
                    "x86_64", "amd64" -> "x64"
                    "aarch64", "arm64" -> "arm64"
                    else -> it
                }
            }

            val version = project.version.toString()
            val baseName = "humandate-fx-showcase-$version-$os-$arch"

            archiveFileName.set("$baseName.zip")
            destinationDirectory.set(layout.buildDirectory.dir("dist"))

            from(layout.buildDirectory.dir("jlink/humandate-fx-showcase")) {
                into(baseName)
            }
        }
