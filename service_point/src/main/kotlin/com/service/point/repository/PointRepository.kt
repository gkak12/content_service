package com.service.point.repository

import com.service.point.domain.entity.Point
import org.springframework.data.jpa.repository.JpaRepository

interface PointRepository: JpaRepository<Point, Long>, PointRepositoryDsl