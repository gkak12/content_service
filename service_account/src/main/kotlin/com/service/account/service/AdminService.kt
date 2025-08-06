package com.service.account.service

import com.service.account.GrpcAdminProtoDto
import com.service.account.GrpcAdminRequest
import com.service.account.GrpcAdminResponse

interface AdminService {

    fun login(protoDto: GrpcAdminProtoDto): GrpcAdminResponse
    fun signup(protoDto: GrpcAdminProtoDto) : GrpcAdminResponse
    fun findAdminByName(userName: GrpcAdminRequest): GrpcAdminResponse
    fun update(protoDto: GrpcAdminProtoDto): GrpcAdminResponse
    fun delete(protoDto: GrpcAdminProtoDto): GrpcAdminResponse
    fun resetPassword(protoDto: GrpcAdminProtoDto): GrpcAdminResponse
}