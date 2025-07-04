package com.service.user.repository.impl

import com.querydsl.jpa.impl.JPAQueryFactory
import com.service.user.domain.entity.User
import com.service.user.repository.UserRepositoryDsl
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryDslImpl (
//    private val jpaQueryFactory: JPAQueryFactory
)
//    : UserRepositoryDsl {
//
//    override fun findInfoByUsername(userName: String): User? {
//        return jpaQueryFactory
//            .select(user)
//            .from(user)
//            .where(user.name.like("%"+userName+"%"))
//            .fetchOne()
//    }
//}