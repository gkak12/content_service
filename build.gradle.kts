buildscript {
    extra["queryDslVersion"] = "5.0.0"
    extra["springBootVersion"] = "3.3.2"
    extra["starterVersion"] = "3.3.2"
}

plugins {
    java
    id("org.jetbrains.kotlin.jvm") version "1.9.23"
    id("org.jetbrains.kotlin.kapt") version "1.9.23"
    id("org.jetbrains.kotlin.plugin.spring") version "1.9.23"
    id("org.springframework.boot") version "3.3.2"
    id("io.spring.dependency-management") version "1.1.6"
    id("com.ewerk.gradle.plugins.querydsl") version "1.0.10"
}

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "groovy")
    apply(plugin = "idea")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    group = "com.service"
    version = "1.0.0"

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(21))
        }
    }

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
    }

    configurations.compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }

    dependencies {
        val starterVersion: String by rootProject.extra

        // Spring Starter
        implementation("org.springframework.boot:spring-boot-starter-data-jdbc:$starterVersion")
        implementation("org.springframework.boot:spring-boot-starter-data-jpa:$starterVersion")
        implementation("org.springframework.boot:spring-boot-starter-jdbc:$starterVersion")
        implementation("org.springframework.boot:spring-boot-starter-web:$starterVersion")

        // Validation
        implementation("org.springframework.boot:spring-boot-starter-validation:$starterVersion")

        // Security
        implementation("org.springframework.boot:spring-boot-starter-security:$starterVersion")
        implementation("io.jsonwebtoken:jjwt-api:0.11.5")
        implementation("io.jsonwebtoken:jjwt-impl:0.11.5")
        implementation("io.jsonwebtoken:jjwt-jackson:0.11.5")

        // DB
        implementation("mysql:mysql-connector-java:8.0.33")

        // Lombok
        compileOnly("org.projectlombok:lombok")
        annotationProcessor("org.projectlombok:lombok")

        // Jasypt
        implementation("com.github.ulisesbocchio:jasypt-spring-boot-starter:3.0.5")

        // Test
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")

        // JSON
        implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
        implementation("com.fasterxml.jackson.core:jackson-databind")
        implementation("com.hubspot.jackson:jackson-datatype-protobuf:0.9.15")

        // Native Query
        implementation("com.github.jsqlparser:jsqlparser:4.9")

        // Base64
        implementation("commons-codec:commons-codec:1.17.0")

        // MapStruct
        implementation("org.mapstruct:mapstruct:1.6.0")
        annotationProcessor("org.mapstruct:mapstruct-processor:1.6.0")

        // QueryDSL
        val queryDslVersion: String by rootProject.extra
        implementation("com.querydsl:querydsl-jpa:${queryDslVersion}:jakarta")
        annotationProcessor("com.querydsl:querydsl-apt:${queryDslVersion}:jakarta")
        annotationProcessor("jakarta.annotation:jakarta.annotation-api")
        annotationProcessor("jakarta.persistence:jakarta.persistence-api")

        // commons
        implementation("org.apache.commons:commons-lang3:3.16.0")

        // UUID
        implementation("com.fasterxml.uuid:java-uuid-generator:5.1.0")

        // p6spy
        implementation("p6spy:p6spy:3.9.1")
    }

    tasks.test {
        useJUnitPlatform()
    }
}

project(":service_content") {
    dependencies {
        compileOnly(project(":module_common"))
    }
}

// build tasks
tasks.named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    enabled = false
}

tasks.register<Exec>("runBoot") {
    commandLine("gradlew.bat", "run")
}

tasks.register("run") {
    doLast {
        println("==============RUN==============")
    }
}

fun configureBootRunArgs(projectPath: String, profile: String = "local") {
    val bootRun = project(projectPath).tasks.findByName("bootRun")
    bootRun?.let {
        (it as? JavaExec)?.args = listOf("--spring.profiles.active=$profile")
    }
}

tasks.register("runAll") {
    val profile = if (project.hasProperty("profile")) project.property("profile") as String else "local"
    dependsOn(":service_content:bootRun")
    doFirst {
        configureBootRunArgs(":service_content", profile)
    }
    enabled = false
}

tasks.register("contentRun") {
    configureBootRunArgs(":service_content")
    finalizedBy(":service_content:bootRun")
}
