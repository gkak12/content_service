package com.service.point

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["com.service"])
class PointApplication

fun main(args: Array<String>) {
    val log = LoggerFactory.getLogger(PointApplication::class.java)
    log.info("Starting MainApplication...")
    runApplication<PointApplication>(*args)
}
