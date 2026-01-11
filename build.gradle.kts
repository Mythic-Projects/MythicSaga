plugins {
    `java-library`
    `maven-publish`

    kotlin("jvm") version "2.2.21" apply false
    id("idea")
}

idea {
    project.jdkName = "21"
}

allprojects {
    group = "org.mythicprojects.saga"
    version = "0.1.0-SNAPSHOT"

    apply(plugin = "java-library")
    apply(plugin = "kotlin")
    apply(plugin = "maven-publish")
    
    repositories {
        mavenCentral()
    }
    
    publishing {
        publications {
            create<MavenPublication>("libraries") {
                artifactId = project.name.lowercase()
                from(components["java"])

                pom {
                    url.set("https://github.com/Mythic-Projects/MythicSaga")
                    name.set(project.name)
                    description.set("A lightweight and flexible Saga pattern implementation for Java projects.")

                    developers {
                        developer {
                            id.set("peridot")
                            name.set("Peridot")
                            email.set("peridot491@pm.me")
                        }
                    }

                    scm {
                        url.set("https://github.com/Mythic-Projects/MythicSaga.git")
                        connection.set("git@github.com:Mythic-Projects/MythicSaga.git")
                        developerConnection.set("git@github.com:Mythic-Projects/MythicSaga.git")
                    }

                    licenses {
                        license {
                            name.set("The Apache License, Version 2.0")
                            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                            distribution.set("repo")
                        }
                    }
                }
            }
        }

        repositories {
            maven {
                name = "mythicprojects"
                val versionString = version.toString()
                url = uri("https://repo.mythicprojects.org/${if (versionString.endsWith("SNAPSHOT")) "snapshots" else "releases"}")

                credentials(PasswordCredentials::class)
                authentication {
                    create<BasicAuthentication>("basic")
                }
            }
        }
    }
}

subprojects {
    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(21)
        }

        withSourcesJar()
        withJavadocJar()
    }

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
    }

    tasks.withType<Javadoc> {
        options {
            (this as CoreJavadocOptions).addStringOption(
                "Xdoclint:none",
                "-quiet"
            ) // mute warnings
        }
    }
}
