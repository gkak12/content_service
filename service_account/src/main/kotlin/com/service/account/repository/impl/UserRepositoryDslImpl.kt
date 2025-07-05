package com.service.account.repository.impl

import com.querydsl.jpa.impl.JPAQueryFactory
import com.service.account.domain.entity.User
import com.service.account.repository.UserRepositoryDsl
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryDslImpl (
    private val jpaQueryFactory: JPAQueryFactory
) : UserRepositoryDsl {

    override fun findInfoByUsername(userName: String): User? {
//        jpaQueryFactory.selectFrom(user)

        return null
//        return jpaQueryFactory
//            .select(user)
//            .from(user)
//            .where(user.name.like("%"+userName+"%"))
//            .fetchOne()
    }
}