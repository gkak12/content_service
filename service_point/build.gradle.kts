plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("kapt")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("application")
}

application {
    mainClass.set("com.service.point.PointApplicationKt")
}

springBoot {
    mainClass.set("com.service.point.PointApplicationKt")
}

tasks.named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    mainClass.set("com.service.point.PointApplicationKt")
}

dependencies {
    implementation(project(":module_common"))
    implementation(project(":module_grpc"))

    implementation("net.devh:grpc-server-spring-boot-starter:3.1.0.RELEASE")

    implementation("p6spy:p6spy:3.9.1")
    implementation("com.github.gavlyukovskiy:datasource-decorator-spring-boot-autoconfigure:1.9.0")

    implementation(kotlin("stdlib"))
}

kotlin {
    sourceSets["main"].kotlin.srcDir("build/generated/source/kapt/main")
}

tasks.named("bootRun") {
    enabled = true
}
