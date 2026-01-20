plugins {
    java
    id("com.gradleup.shadow") version "9.3.1"
    id("maven-publish")
}

group = "com.example"
version = "1.0.0"

repositories {
    mavenCentral()
    mavenLocal()
}

// You need to run ./gradlew publishToMavenLocal

publishing {
    publications {
        create<MavenPublication>("hytaleServer") {
            groupId = "com.hypixel.hytale"
            artifactId = "HytaleServer-parent"
            version = "1.0-SNAPSHOT"
            // Path to the hytale server JAR file (in my case below)
            artifact(file("/home/paique/Downloads/hytale-downloader/2026.01.17-4b0f30090/Server/HytaleServer.jar"))
        }
    }
    repositories {
        mavenLocal()
    }
}


dependencies {
    compileOnly("com.hypixel.hytale:HytaleServer-parent:1.0-SNAPSHOT")

    implementation("com.google.guava:guava:32.1.3-jre")
    implementation("com.google.code.gson:gson:2.10.1")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}

tasks.test {
    useJUnitPlatform()
}

tasks.shadowJar {
    archiveClassifier.set("")

    relocate("com.google.gson", "com.example.myplugin.libs.gson")
}

tasks.build {
    dependsOn(tasks.shadowJar)
}

tasks.processResources {
    filesMatching("manifest.json") {
        expand(
            "version" to project.version,
            "name" to project.name
        )
    }
}

buildscript {
    dependencies {
        classpath(files("/home/paique/hytale-live/buildSrc/build/libs/buildSrc.jar"))
    }
}
apply(plugin = "com.hytale.server")
