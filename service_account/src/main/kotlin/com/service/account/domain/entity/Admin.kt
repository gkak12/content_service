package com.service.account.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "ADMIN")
class Admin (

    @Id
    @Column(name = "ADMIN_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var adminSeq: Long = 0,

    @Column(name = "ADMIN_ID")
    var adminId: String = "",

    @Column(name = "ADMIN_PASSWORD")
    var adminPassword: String = "",

    @Column(name = "ADMIN_NAME")
    var adminName: String = "",

    @Column(name = "ADMIN_TYPE")
    var adminType: String = "",

    @Column(name = "EMAIL")
    var email: String = ""
)