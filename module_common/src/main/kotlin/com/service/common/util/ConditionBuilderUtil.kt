package com.service.common.util

import com.querydsl.core.types.dsl.*
import java.time.LocalDate
import java.time.LocalDateTime

object ConditionBuilderUtil {

    fun <T> buildEquals(path: SimpleExpression<T>?, value: T?): BooleanExpression? {
        if (path == null || value == null) return null

        return when (value) {
            is String -> if (value.isNotEmpty()) path.eq(value) else null
            is Long, is Int, is Double -> path.eq(value)
            is Boolean -> path.eq(value)
            else -> null
        }
    }

    fun buildStringLike(path: StringPath?, value: String?): BooleanExpression? {
        if (path == null || value.isNullOrBlank()) return null
        return path.like("%$value%")
    }

    fun buildAmountBetween(path: NumberPath<Long>?, startAmountVal: Long?, endAmountVal: Long?): BooleanExpression? {
        if (path == null) return null

        return when {
            startAmountVal != null && endAmountVal != null && startAmountVal <= endAmountVal ->
                path.between(startAmountVal, endAmountVal)
            startAmountVal != null -> path.goe(startAmountVal)
            endAmountVal != null -> path.loe(endAmountVal)
            else -> null
        }
    }

    fun buildDateBetween(path: DateTimePath<LocalDateTime>?, startDate: LocalDate?, endDate: LocalDate?): BooleanExpression? {
        if (path == null) return null

        return when {
            startDate != null && endDate != null && startDate.isBefore(endDate) ->
                path.between(startDate.atStartOfDay(), endDate.atTime(23, 59, 59))
            startDate != null -> path.goe(startDate.atStartOfDay())
            endDate != null -> path.loe(endDate.atTime(23, 59, 59))
            else -> null
        }
    }

    fun buildDateTimeBetween(path: DateTimePath<LocalDateTime>?, startDateTime: LocalDateTime?, endDateTime: LocalDateTime?): BooleanExpression? {
        if (path == null) return null

        return when {
            startDateTime != null && endDateTime != null && startDateTime.isBefore(endDateTime) ->
                path.between(startDateTime, endDateTime)
            startDateTime != null -> path.goe(startDateTime)
            endDateTime != null -> path.loe(endDateTime)
            else -> null
        }
    }
}