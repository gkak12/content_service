package com.service.point.domain.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "POINT_DETAIL")
class PointDetail (

    @Id
    @Column(name = "POINT_DETAIL_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var pointDetailSeq: Long = 0,

    @Column(name = "POINT_SEQ")
    var pointSeq: Long = 0,

    @Column(name = "ACCUMULATION_SEQ")
    var accumlationSeq: Long = 0,

    @Column(name = "USER_SEQ")
    var userSeq: Long = 0,

    @Column(name = "TYPE_SEQ")
    var typeSeq: Long = 0,

    @Column(name = "AMOUNT")
    var amount: Long = 0,

    @Column(name = "CONTENT")
    var content: String = "",

    @Column(name = "CREATION_DATE_TIME")
    var creationDateTime: LocalDateTime? = null,

    @Column(name = "EXPIRATION_DATE_TIME")
    var expirationDateTime: LocalDateTime? = null
)