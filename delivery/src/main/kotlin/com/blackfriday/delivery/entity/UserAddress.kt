package com.blackfriday.delivery.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.Table

@Entity
@Table(indexes = [Index(name = "idx_userId", columnList = "userId")])
data class UserAddress(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    val userId: Long,
    val address: String,
    val alias: String,
) {
    constructor(
        userId: Long,
        address: String,
        alias: String,
    ) : this(
        null,
        userId,
        address,
        alias,
    )
}
