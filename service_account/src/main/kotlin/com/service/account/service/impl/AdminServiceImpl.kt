package com.service.account.service.impl

import com.service.account.GrpcAdminProtoDto
import com.service.account.GrpcAdminRequest
import com.service.account.GrpcAdminResponse
import com.service.account.domain.mapper.AdminMapper
import com.service.account.repository.AdminRepository
import com.service.account.service.AdminService
import com.service.grpc.enums.StatusEnum
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AdminServiceImpl (
    private val adminRepository : AdminRepository,
    private val adminMapper: AdminMapper,
    private val passwordEncoder: PasswordEncoder
): AdminService{

    override fun login(protoDto: GrpcAdminProtoDto): GrpcAdminResponse {
        val admin = adminRepository.login(protoDto.adminId)
            ?: throw NullPointerException("${protoDto.adminId} is not existed.")

        val flag = passwordEncoder.matches(protoDto.adminPassword, admin.adminPassword)

        if(!flag){
            throw BadCredentialsException("${protoDto.adminId} is not valid.")
        }

        return GrpcAdminResponse.newBuilder()
            .setStatusCode(StatusEnum.OK.value)
            .setMessage("${protoDto.adminId} logged in as ${admin.adminName}")
            .build()
    }

    override fun findAdminByName(grpcAdminRequest: GrpcAdminRequest): GrpcAdminResponse {
        val userName = grpcAdminRequest.adminName
        val list = adminRepository.findAdminByName(userName).stream()
                .map { adminMapper.toProtoDto(it) }
                .toList()

        return GrpcAdminResponse.newBuilder()
            .setStatusCode(StatusEnum.OK.value)
            .addAllDto(list)
            .build()
    }
}