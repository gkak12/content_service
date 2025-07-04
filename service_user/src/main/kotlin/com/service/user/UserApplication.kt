package com.service.user

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class UserApplication

fun main(args: Array<String>) {
    val log = LoggerFactory.getLogger(UserApplication::class.java)
    log.info("Starting MainApplication...")
    runApplication<UserApplication>(*args)
}
