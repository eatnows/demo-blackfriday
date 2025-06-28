package com.blackfriday.delivery.entity

import com.blackfriday.delivery.enums.DeliveryStatus
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.Table

@Entity
@Table(indexes = [Index(name = "idx_orderId", columnList = "orderId")])
data class Delivery(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    val orderId: Long,
    val productName: String,
    val productCount: Long,
    val address: String,
    val referenceCode: Long,
    var deliveryStatus: DeliveryStatus,
) {
    constructor(
        orderId: Long,
        productName: String,
        productCount: Long,
        address: String,
        referenceCode: Long,
        deliveryStatus: DeliveryStatus
    ) : this(
        null,
        orderId,
        productName,
        productCount,
        address,
        referenceCode,
        deliveryStatus,
    )
}
