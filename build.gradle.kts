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
}

subprojects {
    repositories {
        mavenCentral()
    }
    
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
