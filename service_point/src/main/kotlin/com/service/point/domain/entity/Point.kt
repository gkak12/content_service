package com.service.point.domain.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "POINT")
class Point (

    @Id
    @Column(name = "POINT_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var pointSeq: Long = 0,

    @Column(name = "USER_SEQ")
    var userSeq: Long = 0,

    @Column(name = "TYPE_SEQ")
    var typeSeq: Long = 0,

    @Column(name = "ORDER_SEQ")
    var orderSeq: Long = 0,

    @Column(name = "AMOUNT")
    var amount: Long = 0,

    @Column(name = "CONTENT")
    var content: String = "",

    @Column(name = "CREATION_DATE_TIME")
    var creationDateTime: LocalDateTime? = null,

    @Column(name = "EXPIRATION_DATE_TIME")
    var expirationDateTime: LocalDateTime? = null
)