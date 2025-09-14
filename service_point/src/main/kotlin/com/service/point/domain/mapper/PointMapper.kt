package com.service.point.domain.mapper

import com.service.point.GrpcPointProtoDto
import com.service.point.domain.entity.Point
import org.mapstruct.*

@Mapper(
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = "spring"
)
interface PointMapper {

    fun toEntity(protoDto: GrpcPointProtoDto): Point
    fun toProtoDto(point: Point): GrpcPointProtoDto
}