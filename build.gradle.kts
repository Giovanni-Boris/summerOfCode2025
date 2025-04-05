plugins {
    `kotlin-dsl`
    `maven-publish`
    id("org.example.sumerOfCode-source-strcuture-plugin") version "1.0.0"
}

group = "org.example.sumerOfCode"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

gradlePlugin {
    plugins {
        create("SourceStructurePlugin") {
            id = "org.example.sumerOfCode-source-strcuture-plugin"
            implementationClass = "SourceStructurePlugin"
        }
    }
}


kotlin {
    jvmToolchain(8)
}