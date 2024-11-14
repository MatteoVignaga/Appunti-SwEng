plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("junit:junit:4.13.1")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.mockito:mockito-core:5.4.0")
    testImplementation("org.mockito:mockito-junit-jupiter:5.4.0")
    testImplementation("org.assertj:assertj-core:3.24.2")
    implementation("org.mockito:mockito-core:5.4.0")
    implementation("org.mockito:mockito-junit-jupiter:5.4.0")
    implementation("org.assertj:assertj-core:3.24.2")
}

tasks.test {
    useJUnitPlatform()
}