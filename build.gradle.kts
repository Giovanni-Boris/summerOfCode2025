plugins {
    `kotlin-dsl`
    `maven-publish`
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
            id = "org.example.sumerOfCode"
            implementationClass = "SourceStructurePlugin"
        }
    }
}


kotlin {
    jvmToolchain(8)
}