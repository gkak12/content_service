package com.service.grpc.config

import io.grpc.ServerInterceptor
import com.service.grpc.exception.GrpcExceptionHandler
import net.devh.boot.grpc.server.interceptor.GrpcGlobalServerInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GrpcConfig {

    @Bean
    @GrpcGlobalServerInterceptor
    fun exceptionHandlingInterceptor(): ServerInterceptor {
        return GrpcExceptionHandler()
    }
}