plugins {
    `java-library`
    `maven-publish`
    signing
    id("org.openjfx.javafxplugin") version "0.1.0"
}

group = "com.infoyupay.humandate"
version = "1.0.0"

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])

            pom {
                name.set("humandate-fx")
                description.set(
                    "JavaFX adapter library for humandate-core, providing " +
                            "human-friendly date bindings, converters and UI helpers."
                )
                url.set("https://github.com/infoyupay/humandate-fx")

                licenses {
                    license {
                        name.set("Apache License, Version 2.0")
                        url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }

                developers {
                    developer {
                        id.set("dvidal")
                        name.set("David Vidal")
                        email.set("dvidal@infoyupay.com")
                        organization.set("InfoYupay")
                        organizationUrl.set("https://infoyupay.com")
                    }
                }

                scm {
                    connection.set("scm:git:https://github.com/infoyupay/humandate-fx.git")
                    developerConnection.set("scm:git:ssh://git@github.com:infoyupay/humandate-fx.git")
                    url.set("https://github.com/infoyupay/humandate-fx")
                }
            }
        }
    }
}

signing {
    useGpgCmd()
    sign(publishing.publications)
}

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

    testImplementation(platform("org.junit:junit-bom:6.0.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

    testImplementation("org.assertj:assertj-core:3.27.6")
}

tasks.test {
    useJUnitPlatform()
}
