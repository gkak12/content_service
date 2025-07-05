package com.service.account.repository.impl

import com.querydsl.jpa.impl.JPAQueryFactory
import com.service.account.domain.entity.QUser.user
import com.service.account.domain.entity.User
import com.service.account.repository.UserRepositoryDsl
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryDslImpl (
    private val jpaQueryFactory: JPAQueryFactory
) : UserRepositoryDsl {

    override fun findUserByName(userName: String): User? {
        return jpaQueryFactory
            .select(user)
            .from(user)
            .where(user.userName.like("%"+userName+"%"))
            .fetchOne()
    }
}