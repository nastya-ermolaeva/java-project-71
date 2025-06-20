plugins {
    application
    id("com.github.ben-manes.versions") version "0.52.0"
    id("checkstyle")
    id("org.sonarqube") version "6.2.0.5505"
    id("jacoco")
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
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.17.0")
}

checkstyle {
    toolVersion = "10.17.0"
    configFile = rootProject.file("config/checkstyle/checkstyle.xml")
}

jacoco {
    toolVersion = "0.8.11"
}

tasks.test {
    useJUnitPlatform()
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
        html.required.set(true)
        csv.required.set(false)
    }
}

sonar {
    properties {
        property("sonar.projectKey", "nastya-ermolaeva_java-project-71")
        property("sonar.organization", "nastya-ermolaeva")
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.coverage.jacoco.xmlReportPaths", "${buildDir}/reports/jacoco/test/jacocoTestReport.xml")
    }
}