package com.service.account.repository

import com.service.account.domain.entity.Admin

interface AdminRepositoryDsl {

    fun findAdminByName(name: String): Admin?
}