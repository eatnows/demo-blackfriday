package com.blackfriday.member.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @Column(unique = true)
    var loginId: String,

    var userName: String,
) {
    constructor(
        loginId: String,
        userName: String,
    ) : this(
        null,
        loginId,
        userName
    )
}
