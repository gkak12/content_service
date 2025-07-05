package com.service.account.repository

import com.service.admin.domain.entity.Admin
import org.springframework.data.jpa.repository.JpaRepository

interface AdminRepository: JpaRepository<Admin, Long>, AdminRepositoryDsl