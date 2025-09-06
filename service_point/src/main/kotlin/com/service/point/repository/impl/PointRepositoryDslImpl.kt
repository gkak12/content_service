package com.service.point.repository.impl

import com.querydsl.jpa.impl.JPAQueryFactory
import com.service.point.domain.entity.Point
import com.service.point.domain.entity.QPoint.point
import com.service.point.repository.PointRepositoryDsl
import org.springframework.stereotype.Repository

@Repository
class PointRepositoryDslImpl (
    private val jpaQueryFactory: JPAQueryFactory
): PointRepositoryDsl{

    override fun findPointById(pointSeq: Long): Point? {
        return jpaQueryFactory
            .select(point)
            .from(point)
            .where(point.pointSeq.eq(pointSeq))
            .fetchOne()
    }

    override fun findCancellationEmoney(orderSeq: Long): List<Point> {
        return jpaQueryFactory
            .select(point)
            .from(point)
            .where(point.orderSeq.eq(orderSeq))
            .fetch()
    }
}