package com.service.account.service

import com.service.account.GrpcAdminResponse

interface AdminService {

    fun findAdminByName(userName: String): GrpcAdminResponse
}