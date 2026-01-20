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

val hytaleFile = file("/home/paique/Downloads/hytale-downloader/2026.01.17-4b0f30090/Server/HytaleServer.jar")

if (!hytaleFile.exists()) {
    throw GradleException("Hytale server JAR file not found at ${hytaleFile.absolutePath}!! You need to download it and put the path at build.gradle.kts:15")
}

// You need to run ./gradlew publishToMavenLocal
publishing {
    publications {
        create<MavenPublication>("hytaleServer") {
            groupId = "com.hypixel.hytale"
            artifactId = "HytaleServer-parent"
            version = "1.0-SNAPSHOT"
            artifact(hytaleFile)
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

// Reserved for future usage, see
//buildscript {
//    dependencies {
//        classpath(files("/home/paique/hytale-live/buildSrc/build/libs/buildSrc.jar"))
//    }
//}
//apply(plugin = "com.hytale.server")
