package com.service.account.domain.mapper

import com.service.account.GrpcUserRequest
import com.service.account.GrpcUserResponse
import com.service.account.domain.entity.User
import org.mapstruct.*

@Mapper(
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = "spring"
)
interface UserMapper {

    fun toEntity(grpcUserRequest : GrpcUserRequest) : User
    fun toProtoDto(user : User) : GrpcUserResponse
}