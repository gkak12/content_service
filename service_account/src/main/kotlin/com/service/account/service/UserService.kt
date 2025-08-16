package com.service.account.service

import com.service.account.GrpcUserProtoDto
import com.service.account.GrpcUserRequest
import com.service.account.GrpcUserResponse

interface UserService {

    fun findUserById(request: GrpcUserRequest): GrpcUserResponse
    fun findUserByName(request: GrpcUserRequest): GrpcUserResponse
    fun loginUser(protoDto: GrpcUserProtoDto): GrpcUserResponse
}