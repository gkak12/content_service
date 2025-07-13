package com.service.grpc.exception

import com.service.grpc.enums.GrpcErrorCodeEnum
import io.grpc.Metadata
import io.grpc.Status
import io.grpc.StatusRuntimeException

class GrpcCustomException : StatusRuntimeException {

    val errorCode: String

    constructor() : this(
        GrpcErrorCodeEnum.NOT_FOUND.value,
        GrpcErrorCodeEnum.NOT_FOUND.desc,
        GrpcErrorCodeEnum.NOT_FOUND.status
    )

    constructor(errorCodeEnum: GrpcErrorCodeEnum) : this(
        errorCodeEnum.value,
        errorCodeEnum.desc,
        errorCodeEnum.status
    )

    constructor(errorCodeEnum: GrpcErrorCodeEnum, message: String) : this(
        errorCodeEnum.value,
        message,
        errorCodeEnum.status
    )

    constructor(errorCode: String, errorMessage: String, status: Status) :
            super(status.withDescription(errorMessage)) {
        this.errorCode = errorCode
    }

    fun getMetadata(): Metadata {
        return Metadata().apply {
            put(Metadata.Key.of("error-code", Metadata.ASCII_STRING_MARSHALLER), errorCode)
        }
    }
}
