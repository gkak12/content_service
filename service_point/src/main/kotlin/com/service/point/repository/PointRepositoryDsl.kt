package com.service.point.repository

import com.service.point.domain.entity.Point

interface PointRepositoryDsl {

    fun findPointById(pointSeq: Long): Point?
    fun findCancellationEmoney(orderSeq: Long): List<Point>
}