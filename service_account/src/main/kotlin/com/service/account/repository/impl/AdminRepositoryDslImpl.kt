package com.service.account.repository.impl

import com.querydsl.jpa.impl.JPAQueryFactory
import com.service.account.domain.entity.Admin
import com.service.account.domain.entity.QAdmin.admin
import com.service.account.repository.AdminRepositoryDsl
import org.springframework.stereotype.Repository

@Repository
class AdminRepositoryDslImpl (
    private val jpaQueryFactory: JPAQueryFactory
): AdminRepositoryDsl {

    override fun findAdminById(id: String): Admin? {
        return jpaQueryFactory
            .select(admin)
            .from(admin)
            .where(admin.adminId.eq(id))
            .fetchOne()
    }

    override fun findAdminByName(name: String): List<Admin> {
        return jpaQueryFactory
            .select(admin)
            .from(admin)
            .where(admin.adminName.like("%"+name+"%"))
            .fetch()
    }
}