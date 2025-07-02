plugins {
    id("java-library")
    id("org.springframework.boot") version "3.3.2"
}

dependencies {
    implementation(project(":module_common"))

    implementation("p6spy:p6spy:3.9.1")
    implementation("com.github.gavlyukovskiy:datasource-decorator-spring-boot-autoconfigure:1.9.0")
}

// 테스트 관련 설정이 주석 처리되어 있어 그대로 둠 (원할 경우 주석 제거)
//// tasks.named("test") {
////     useJUnitPlatform()
//// }

tasks.named<org.springframework.boot.gradle.tasks.run.BootRun>("bootRun") {
    enabled = true
}