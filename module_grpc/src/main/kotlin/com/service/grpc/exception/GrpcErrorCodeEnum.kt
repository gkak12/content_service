package com.service.grpc.exception

import io.grpc.Status

enum class GrpcErrorCodeEnum(
    val value: String,
    val desc: String,
    val status: Status
) {
    VALIDATION_CHECK("400", "잘못된 요청입니다.", Status.INVALID_ARGUMENT),
    UNAUTHENTICATED("401", "인증되지 않은 사용자입니다.", Status.UNAUTHENTICATED),
    PERMISSION_DENIED("403", "권한이 없는 사용자입니다.", Status.PERMISSION_DENIED),
    NOT_FOUND("404", "요청하신 정보가 존재하지 않습니다.", Status.NOT_FOUND),
    ALREADY_EXIST("409", "이미 존재하는 정보입니다.", Status.ALREADY_EXISTS),
    RESOURCE_GONE("410", "이미 삭제된 정보입니다.", Status.ABORTED),
    INTERNAL_SERVER_ERROR("500", "서버에서 알 수 없는 오류가 발생하였습니다.", Status.INTERNAL);
}
