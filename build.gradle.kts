plugins {
    // Apply the java-library plugin to add support for Java Library
    `java-library`
    // Idea plugin
    idea
    // Code coverage
    jacoco
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    // This dependency is exported to consumers, that is to say found on their compile classpath.
    api("org.apache.commons:commons-math3:3.6.1")

    // This dependency is used internally, and not exposed to consumers on their own compile classpath.
    implementation("com.google.guava:guava:29.0-jre")

    // Spring framework
    implementation("org.springframework:spring-web:5.2.8.RELEASE")
    implementation("org.springframework.security:spring-security-crypto:5.3.3.RELEASE")

    // Fasterxml jackson
    implementation("com.fasterxml.jackson.core:jackson-databind:2.11.1")

    // Use JUnit test framework
    testImplementation("junit:junit:4.13")
}

tasks.test {
    // Report is always generated after tests run
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    // Tests are required to run before generating the report
    dependsOn(tasks.test)
    reports {
        html.isEnabled = true
        html.destination = file("${buildDir}/reports/coverage")
    }
}