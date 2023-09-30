plugins {
    id("java-library")
    id("org.springframework.boot") version "3.1.4"
    id("io.spring.dependency-management") version "1.1.3"
}

group = "ru.Pavel"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("org.postgresql:postgresql:42.6.0")
    implementation("org.springframework.boot:spring-boot-starter-web:3.1.4")
    implementation("com.google.code.gson:gson:2.10.1")
}


tasks.bootRun {
}

tasks.test {
    useJUnitPlatform()
}