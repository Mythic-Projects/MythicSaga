MythicSaga
===========
[![License: Apache 2.0](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
![Version](https://repo.mythicprojects.org/api/badge/latest/snapshots/org/mythicprojects/saga/mythicsaga-core?color=d45f48&name=Snapshots&prefix=v)
![GitHub repo size](https://img.shields.io/github/repo-size/Mythic-Projects/MythicSaga)

A lightweight and flexible Saga pattern implementation for Java projects.

## Overview

MythicSaga provides simple local transaction management using the Saga pattern.
With simple and fluent block-based workflow definitions, allowing developers to easily define complex transactions with compensating actions in case of failures.

## Requirements

- Java 21 or higher

## Installation

### Gradle
```kotlin
repositories {
    // Releases
    maven {
        url = uri("https://repo.mythicprojects.org/releases")
    }
    // Snapshots
    maven {
        url = uri("https://repo.mythicprojects.org/snapshots")
    }
}
```

```kotlin
dependencies {
    implementation("org.mythicprojects.saga:[module]:[version]")
}
```

### Maven

```xml
<!-- Releases -->
<repository>
    <id>mythic-releases</id>
    <url>https://repo.mythicprojects.org/releases</url>
</repository>
<!-- Snapshots -->
<repository>
    <id>mythic-snapshots</id>
    <url>https://repo.mythicprojects.org/snapshots</url>
</repository>
```

```xml
<dependency>
    <groupId>org.mythicprojects.saga</groupId>
    <artifactId>mythicsaga-core</artifactId>
    <version>[version]</version>
</dependency>
```


## License

Licensed under the [Apache License, Version 2.0](LICENSE)

