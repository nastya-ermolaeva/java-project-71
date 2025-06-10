plugins {
    application
    id("java")
    id("com.github.ben-manes.versions") version "0.52.0"
    id("checkstyle")
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("hexlet.code.App")
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("info.picocli:picocli:4.7.7")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.0")
}

checkstyle {
    toolVersion = "10.17.0"
    configFile = rootProject.file("config/checkstyle/checkstyle.xml")
}

tasks.test {
    useJUnitPlatform()
}