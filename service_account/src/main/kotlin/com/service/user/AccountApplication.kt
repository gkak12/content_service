package com.service.user

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AccountApplication

fun main(args: Array<String>) {
    val log = LoggerFactory.getLogger(AccountApplication::class.java)
    log.info("Starting MainApplication...")
    runApplication<AccountApplication>(*args)
}
