package com.service.account.repository

import com.service.account.domain.entity.User

interface UserRepositoryDsl {

    fun findInfoByUsername(userId: String): User?
}