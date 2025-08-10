package com.service.account.repository

import com.service.account.domain.entity.User

interface UserRepositoryDsl {

    fun findUserById(userId: String): User?
    fun findUserByName(userName: String): List<User>
}