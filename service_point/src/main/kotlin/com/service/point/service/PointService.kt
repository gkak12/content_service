package com.service.point.service

import com.service.point.GrpcPointRequest
import com.service.point.GrpcPointResponse

interface PointService {

    fun findPointById(pointRequest: GrpcPointRequest): GrpcPointResponse
    fun findPointPaging(pointRequest: GrpcPointRequest): GrpcPointResponse
}