package com.service.grpc.config

import net.devh.boot.grpc.server.security.authentication.BasicGrpcAuthenticationReader
import net.devh.boot.grpc.server.security.authentication.GrpcAuthenticationReader
import org.springframework.context.annotation.Bean

class GrpcSecutiryConfig {

    @Bean
    fun grpcAuthenticationReader(): GrpcAuthenticationReader {
        return BasicGrpcAuthenticationReader()
    }
}