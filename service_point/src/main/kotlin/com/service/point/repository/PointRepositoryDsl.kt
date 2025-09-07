package com.service.point.repository

import com.service.point.GrpcPointRequest
import com.service.point.domain.entity.Point
import org.springframework.data.domain.Page

interface PointRepositoryDsl {

    fun findPointPaging(pointRequest: GrpcPointRequest): Page<Point>
    fun findCancellationEmoney(orderSeq: Long): Point?
}