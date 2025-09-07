package com.service.point.repository.impl

import com.querydsl.jpa.impl.JPAQueryFactory
import com.service.point.GrpcPointRequest
import com.service.point.domain.entity.Point
import com.service.point.domain.entity.QPoint.point
import com.service.point.repository.PointRepositoryDsl
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
class PointRepositoryDslImpl (
    private val jpaQueryFactory: JPAQueryFactory
): PointRepositoryDsl{

    override fun findPointPaging(pointRequest: GrpcPointRequest): Page<Point> {
        val pageable: Pageable = PageRequest.of(
            pointRequest.paginationRequest.pageNumber,
            pointRequest.paginationRequest.pageSize
        )

        val list = jpaQueryFactory
            .select(point)
            .from(point)
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .fetch()

        val count = jpaQueryFactory
            .select(point.count())
            .from(point)
            .fetchOne() ?: 0L

        return PageImpl(list, pageable, count)
    }

    override fun findCancellationEmoney(orderSeq: Long): Point? {
        return jpaQueryFactory
            .select(point)
            .from(point)
            .where(point.orderSeq.eq(orderSeq))
            .fetchOne()
    }
}