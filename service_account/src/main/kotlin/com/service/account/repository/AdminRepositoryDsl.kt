package com.service.account.repository

import com.service.admin.domain.entity.Admin

interface AdminRepositoryDsl {

    fun findAdminByName(name: String): Admin?
}