package com.service.account.domain.mapper

import com.service.account.GrpcAdminRequest
import com.service.account.GrpcAdminResponse
import com.service.admin.domain.entity.Admin
import org.mapstruct.*

@Mapper(
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = "spring"
)
interface AdminMapper {

    fun toEntity(request: GrpcAdminRequest): Admin
    fun toProtoDto(admin: Admin): GrpcAdminResponse
}