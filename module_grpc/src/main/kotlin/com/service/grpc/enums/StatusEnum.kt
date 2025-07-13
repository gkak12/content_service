package com.service.grpc.enums

import io.grpc.Status

enum class StatusEnum(
    val value: String,
    val desc: String,
    val status: Status
) {
    OK("OK", "성공", Status.OK),
    FAIL("FAIL", "실패", Status.INTERNAL)
}