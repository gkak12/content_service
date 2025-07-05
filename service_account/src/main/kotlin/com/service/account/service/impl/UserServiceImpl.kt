package com.service.account.service.impl

import com.service.account.GrpcUserResponse
import com.service.account.domain.mapper.UserMapper
import com.service.account.repository.UserRepository
import com.service.account.service.UserService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl (
    private val userRepository: UserRepository,
    private val userMapper: UserMapper
) : UserService {

    override fun findUserByName(userName: String): GrpcUserResponse {
        val user = userRepository.findUserByName(userName) ?: throw NullPointerException("$userName is not found")
        return userMapper.toProtoDto(user)
    }
}
