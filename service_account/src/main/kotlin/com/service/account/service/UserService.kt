package com.service.account.service

import com.service.account.GrpcUserResponse

interface UserService {

    fun findUserByName(userName: String): GrpcUserResponse
}