plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("kapt")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("application")
}

application {
    mainClass.set("com.service.user.UserApplicationKt")
}

springBoot {
    mainClass.set("com.service.user.UserApplicationKt")
}

tasks.named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    mainClass.set("com.service.user.UserApplicationKt")
}

dependencies {
    implementation(project(":module_common"))

    implementation("p6spy:p6spy:3.9.1")
    implementation("com.github.gavlyukovskiy:datasource-decorator-spring-boot-autoconfigure:1.9.0")

    implementation(kotlin("stdlib"))
}

tasks.named("bootRun") {
    enabled = true
}
