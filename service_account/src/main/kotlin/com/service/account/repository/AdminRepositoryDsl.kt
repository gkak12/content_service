package com.service.account.repository

import com.service.account.domain.entity.Admin

interface AdminRepositoryDsl {

    fun findAdminById(name: String): Admin?
    fun findAdminByName(name: String): List<Admin>
}