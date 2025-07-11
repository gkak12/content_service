package com.service.account.repository

import com.service.account.domain.entity.Admin
import org.springframework.data.jpa.repository.JpaRepository

interface AdminRepository: JpaRepository<Admin, Long>, AdminRepositoryDsl