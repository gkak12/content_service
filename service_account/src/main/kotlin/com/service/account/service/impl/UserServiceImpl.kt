package com.service.account.service.impl

import com.service.account.GrpcUserProtoDto
import com.service.account.GrpcUserRequest
import com.service.account.GrpcUserResponse
import com.service.account.domain.mapper.UserMapper
import com.service.account.repository.UserRepository
import com.service.account.service.UserService
import com.service.grpc.enums.StatusEnum
import org.springframework.stereotype.Service

@Service
class UserServiceImpl (
    private val userRepository: UserRepository,
    private val userMapper: UserMapper
): UserService {

    override fun findUserById(request: GrpcUserRequest): GrpcUserResponse {
        val userId = request.userId
        val user = userRepository.findUserById(userId) ?: throw NullPointerException("$userId is not found")

        return GrpcUserResponse.newBuilder()
            .setStatusCode(StatusEnum.OK.value)
            .addDto(userMapper.toProtoDto(user))
            .build()
    }

    override fun findUserByName(request: GrpcUserRequest): GrpcUserResponse {
        val userName = request.userName
        val userList = userRepository.findUserByName(userName)

        return GrpcUserResponse.newBuilder()
            .setStatusCode(StatusEnum.OK.value)
            .addAllDto(userList.stream()
                .map { userMapper.toProtoDto(it) }
                .toList()
            )
            .build()
    }

    override fun createUser(protoDto: GrpcUserProtoDto): GrpcUserResponse {
        val user = userMapper.toEntity(protoDto)
        userRepository.save(user)

        return GrpcUserResponse.newBuilder()
            .setStatusCode(StatusEnum.OK.value)
            .setMessage("${protoDto.userId} is created.")
            .build()
    }
}
