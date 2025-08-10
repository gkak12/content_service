package com.service.account.domain.mapper

import com.service.account.GrpcUserProtoDto
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

    fun toEntity(ProtoDto: GrpcUserProtoDto) : User

    @Mapping(source = "userPassword", target = "userPassword", ignore = true)
    fun toProtoDto(user: User) : GrpcUserProtoDto
}