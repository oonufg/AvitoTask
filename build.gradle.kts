plugins {
    id("java")
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
    implementation("org.springframework:spring-core:6.0.11")
    implementation("org.springframework:spring-beans:6.0.11")
    implementation("org.springframework:spring-context:6.0.11")
    implementation("org.springframework:spring-web:6.0.11")
    implementation("org.springframework:spring-webmvc:6.0.11")
    compileOnly("jakarta.servlet:jakarta.servlet-api:6.0.0")
    implementation("com.google.code.gson:gson:2.10.1")





}

tasks.test {
    useJUnitPlatform()
}