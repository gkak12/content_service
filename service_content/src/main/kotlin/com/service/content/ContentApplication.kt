package com.service.content

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.service.content"])
class ContentApplication

fun main(args: Array<String>) {
    val log = LoggerFactory.getLogger(ContentApplication::class.java)
    log.info("Starting MainApplication...")
    runApplication<ContentApplication>(*args)
}