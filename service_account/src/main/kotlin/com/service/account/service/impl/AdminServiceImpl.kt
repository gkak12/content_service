package com.service.account.service.impl

import com.service.account.GrpcAdminResponse
import com.service.account.domain.mapper.AdminMapper
import com.service.account.repository.AdminRepository
import com.service.account.service.AdminService
import org.springframework.stereotype.Service

@Service
class AdminServiceImpl (
    private val adminRepository : AdminRepository,
    private val adminMapper: AdminMapper
): AdminService{

    override fun findAdminByName(userName: String): GrpcAdminResponse {
        val list = adminRepository.findAdminByName(userName).stream()
                .map { adminMapper.toProtoDto(it) }
                .toList()

        return GrpcAdminResponse.newBuilder()
            .addAllDto(list)
            .build()
    }
}