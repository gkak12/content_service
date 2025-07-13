package com.service.account.repository

import com.service.account.domain.entity.Admin

interface AdminRepositoryDsl {

    fun login(name: String): Admin?
    fun findAdminByName(name: String): List<Admin>
}