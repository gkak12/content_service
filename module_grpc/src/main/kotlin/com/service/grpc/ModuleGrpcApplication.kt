package com.service.grpc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ModuleGrpcApplication

fun main(args: Array<String>) {
    runApplication<ModuleGrpcApplication>(*args)
}
