package com.blackfriday.catalog.mysql.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class SellerProduct(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    val sellerId: Long,
) {
    constructor(sellerId: Long) : this(
        null,
        sellerId
    )
}
