import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

buildscript {
    extra.apply {
        set("queryDslVersion", "5.0.0")
        set("springBootVersion", "3.3.2")
        set("starterVersion", "3.3.2")
    }
}

plugins {
    java
    groovy
    idea
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.spring") version "1.9.23"
    kotlin("plugin.jpa") version "1.9.23"
    kotlin("kapt") version "1.9.23"
    id("org.springframework.boot") version "3.3.2"
    id("io.spring.dependency-management") version "1.1.6"
}

allprojects {
    group = "com.content"
    version = "1.0.0"

    repositories {
        mavenCentral()
    }
}

val activeProfile = System.getProperty("spring.profiles.active") ?: "test"
println("------------ profile: $activeProfile ------------")

subprojects {
    apply(plugin = "java")
    apply(plugin = "groovy")
    apply(plugin = "idea")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(21))
        }
    }

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
    }

    configurations {
        create("compileOnly") {
            extendsFrom(configurations.getByName("annotationProcessor"))
        }
    }

    dependencies {
        val starterVersion: String by rootProject.extra

        // Spring Starters
        implementation("org.springframework.boot:spring-boot-starter-web:$starterVersion")
        implementation("org.springframework.boot:spring-boot-starter-validation:$starterVersion")
        implementation("org.springframework.boot:spring-boot-starter-security:$starterVersion")
        implementation("org.springframework.boot:spring-boot-starter-data-jpa:$starterVersion")
        implementation("org.springframework.boot:spring-boot-starter-data-jdbc:$starterVersion")
        implementation("org.springframework.boot:spring-boot-starter-jdbc:$starterVersion")

        // JWT
        implementation("io.jsonwebtoken:jjwt-api:0.11.5")
        implementation("io.jsonwebtoken:jjwt-impl:0.11.5")
        implementation("io.jsonwebtoken:jjwt-jackson:0.11.5")

        // DB
        if (activeProfile == "test") {
            runtimeOnly("org.xerial:sqlite-jdbc:3.42.0.0")
        } else {
            runtimeOnly("org.postgresql:postgresql:42.5.0")
        }

        // Lombok
        compileOnly("org.projectlombok:lombok")
        annotationProcessor("org.projectlombok:lombok")

        // Jasypt
        implementation("com.github.ulisesbocchio:jasypt-spring-boot-starter:3.0.5")

        // Test
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")

        // Jackson / JSON
        implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
        implementation("com.fasterxml.jackson.core:jackson-databind")
        implementation("com.hubspot.jackson:jackson-datatype-protobuf:0.9.15")

        // Native Query / Parser
        implementation("com.github.jsqlparser:jsqlparser:4.9")

        // Base64
        implementation("commons-codec:commons-codec:1.17.0")

        // MapStruct
        implementation("org.mapstruct:mapstruct:1.6.0")
        annotationProcessor("org.mapstruct:mapstruct-processor:1.6.0")

        // Querydsl
        val queryDslVersion: String by rootProject.extra
        implementation("com.querydsl:querydsl-jpa:$queryDslVersion:jakarta")
        annotationProcessor("com.querydsl:querydsl-apt:$queryDslVersion:jakarta")
        annotationProcessor("jakarta.annotation:jakarta.annotation-api")
        annotationProcessor("jakarta.persistence:jakarta.persistence-api")

        // Apache Commons
        implementation("org.apache.commons:commons-lang3:3.16.0")

        // UUID
        implementation("com.fasterxml.uuid:java-uuid-generator:5.1.0")

        // P6Spy
        implementation("p6spy:p6spy:3.9.1")
    }
}

// === Module Dependencies ===

listOf(
    "service_content",
    "service_user"
).forEach { service ->
    project(":$service") {
        dependencies {
            compileOnly(project(":module_common"))
        }
    }
}

// === Global Tasks ===

tasks.named<Test>("test") {
    useJUnitPlatform()
}

// Spring Boot 재패키징을 전역에서 막음
tasks.withType<BootJar> {
    enabled = false
}

// 실행 관련 태스크
tasks.register<Exec>("runBoot") {
    commandLine("gradlew.bat", "run")
}

tasks.register("run") {
    doLast {
        println("==============RUN==============")
    }
}

tasks.register("runAll") {
    val profile = if (project.hasProperty("profile")) project.property("profile") as String else "test"
    dependsOn(
        ":service_content:bootRun",
        ":service_user:bootRun"
    )
    doFirst {
        configureBootRunArgs(":service_content", profile)
        configureBootRunArgs(":service_user", profile)
    }
    enabled = false
}

fun configureBootRunArgs(projectPath: String, profile: String = "local") {
    project(projectPath).tasks.findByName("bootRun")?.let { bootRun ->
        bootRun as JavaExec
        bootRun.args = listOf("--spring.profiles.active=$profile")
    }
}

// 개별 서비스 run task
listOf("content", "user").forEach { name ->
    tasks.register("${name}Run") {
        configureBootRunArgs(":service_${name}")
        finalizedBy(":service_${name}:bootRun")
    }
}
