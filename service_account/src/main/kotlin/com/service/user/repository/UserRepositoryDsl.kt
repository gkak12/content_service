package com.service.user.repository

import com.service.user.domain.entity.User

interface UserRepositoryDsl {

    fun findInfoByUsername(userId: String): User?
}