package com.service.point.service.impl

import com.service.common.PaginationResponse
import com.service.point.GrpcPointRequest
import com.service.point.GrpcPointResponse
import com.service.point.domain.entity.Point
import com.service.point.domain.mapper.PointMapper
import com.service.point.repository.PointRepository
import com.service.point.service.PointService
import org.springframework.stereotype.Service

@Service
class PointServiceImpl (
    private val pointRepository: PointRepository,
    private val pointMapper: PointMapper
): PointService {

    override fun findPointById(pointRequest: GrpcPointRequest): GrpcPointResponse {
        val pointOptional = pointRepository.findById(pointRequest.pointSeq)

        val builder = GrpcPointResponse.newBuilder()

        if (pointOptional.isPresent) {
            val dto = pointMapper.toProtoDto(pointOptional.get())
            builder.addDto(dto)
        }

        builder.setStatusCode("OK")
        return builder.build()
    }

    override fun findPointPaging(pointRequest: GrpcPointRequest): GrpcPointResponse {
        val page = pointRepository.findPointPaging(pointRequest)

        val paginationResponse = PaginationResponse.newBuilder()
            .setTotalItems(page.totalElements)
            .setTotalPages(page.totalPages)
            .build()

        val list = page.stream()
            .map {
                pointMapper.toProtoDto(it as Point)
            }
            .toList()

        return GrpcPointResponse.newBuilder()
            .addAllDto(list)
            .setPaginationResponse(paginationResponse)
            .build()
    }
}