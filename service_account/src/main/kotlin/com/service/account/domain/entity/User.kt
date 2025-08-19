package com.service.account.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "USER")
class User (

    @Id
    @Column(name = "USER_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var userSeq: Long = 0,

    @Column(name = "USER_ID")
    var userId: String = "",

    @Column(name = "USER_PASSWORD")
    var userPassword: String = "",

    @Column(name = "USER_TYPE")
    var userType: String = "",

    @Column(name = "USER_NAME")
    var userName: String = "",

    @Column(name = "USER_EMAIL")
    var userEmail: String = "",

    @Column(name = "USER_ADDRESS")
    var userAddress: String = ""
)