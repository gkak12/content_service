package com.service.account.service.impl

import com.service.account.GrpcAdminProtoDto
import com.service.account.GrpcAdminRequest
import com.service.account.GrpcAdminResponse
import com.service.account.domain.entity.Admin
import com.service.account.domain.mapper.AdminMapper
import com.service.account.repository.AdminRepository
import com.service.account.service.AdminService
import com.service.common.exception.DuplicateAccountException
import com.service.grpc.enums.StatusEnum
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AdminServiceImpl(
    private val adminRepository : AdminRepository,
    private val adminMapper: AdminMapper,
    private val passwordEncoder: PasswordEncoder
): AdminService {

    override fun login(protoDto: GrpcAdminProtoDto): GrpcAdminResponse {
        val admin = adminRepository.findAdminById(protoDto.adminId)
            ?: throw NullPointerException("${protoDto.adminId} is not existed.")

        val flag = passwordEncoder.matches(protoDto.adminPassword, admin.adminPassword)

        if(!flag){
            throw BadCredentialsException("${protoDto.adminId} password is not valid.")
        }

        return GrpcAdminResponse.newBuilder()
            .setStatusCode(StatusEnum.OK.value)
            .setMessage("${protoDto.adminId} is logged in as ${admin.adminName}.")
            .addDto(GrpcAdminProtoDto.newBuilder()
                .setAdminId(admin.adminId)
                .setAdminType(admin.adminType)
                .build()
            )
            .build()
    }

    override fun signup(protoDto: GrpcAdminProtoDto): GrpcAdminResponse {
        val adminId = protoDto.adminId
        val admin = adminRepository.findAdminById(adminId)

        if(admin != null){
            throw DuplicateAccountException("${adminId} is duplicated.")
        }

        adminRepository.save(
            Admin(
                adminId = adminId,
                adminPassword = passwordEncoder.encode(protoDto.adminPassword),
                adminName = protoDto.adminName,
                email = protoDto.email
            )
        )

        return GrpcAdminResponse.newBuilder()
            .setStatusCode(StatusEnum.OK.value)
            .setMessage("${adminId} is signed.")
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

    override fun update(protoDto: GrpcAdminProtoDto): GrpcAdminResponse {
        val admin = adminRepository.findAdminById(protoDto.adminId)
            ?: throw NullPointerException("${protoDto.adminId} is not found.")

        admin.adminName = protoDto.adminName
        admin.email = protoDto.email

        adminRepository.save(admin)

        return GrpcAdminResponse.newBuilder()
            .setStatusCode(StatusEnum.OK.value)
            .setMessage("${protoDto.adminId} is updated.")
            .build()
    }

    override fun delete(protoDto: GrpcAdminProtoDto): GrpcAdminResponse {
        val admin = adminRepository.findAdminById(protoDto.adminId)
            ?: throw NullPointerException("${protoDto.adminId} is not found.")

        adminRepository.delete(admin)

        return GrpcAdminResponse.newBuilder()
            .setStatusCode(StatusEnum.OK.value)
            .setMessage("${protoDto.adminId} is deleted.")
            .build()
    }

    override fun resetPassword(protoDto: GrpcAdminProtoDto): GrpcAdminResponse {
        val admin = adminRepository.findAdminById(protoDto.adminId)
            ?: throw NullPointerException("${protoDto.adminId} is not found.")

        admin.adminPassword = passwordEncoder.encode(protoDto.adminPassword)
        adminRepository.save(admin)

        return GrpcAdminResponse.newBuilder()
            .setStatusCode(StatusEnum.OK.value)
            .setMessage("${protoDto.adminId} password is updated.")
            .build()
    }
}