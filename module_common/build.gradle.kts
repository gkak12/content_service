plugins {
    id("java-library")
    id("org.springframework.boot") version "3.3.2"
}

dependencies {
    implementation("com.github.ulisesbocchio:jasypt-spring-boot-starter:3.0.5")
}

tasks.named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    enabled = false
}

tasks.named<org.springframework.boot.gradle.tasks.run.BootRun>("bootRun") {
    enabled = false
}