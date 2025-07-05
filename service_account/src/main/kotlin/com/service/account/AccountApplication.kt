package com.service.account

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["com.service"])
class AccountApplication

fun main(args: Array<String>) {
    val log = LoggerFactory.getLogger(AccountApplication::class.java)
    log.info("Starting MainApplication...")
    runApplication<AccountApplication>(*args)
}
