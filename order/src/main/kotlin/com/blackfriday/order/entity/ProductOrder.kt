package com.blackfriday.order.entity

import com.blackfriday.order.enums.OrderStatus
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class ProductOrder(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    val userId: Long,
    val productId: Long, // 단일 상품에 대해서만 주문 가능하다고 가정
    val count: Long,

    var orderStatus: OrderStatus,
    var paymentId: Long?,
    var deliveryId: Long?,
) {
    constructor(
        userId: Long,
        productId: Long,
        count: Long,
        orderStatus: OrderStatus,
        paymentId: Long?,
        deliveryId: Long?,
    ) : this(
        null,
        userId,
        productId,
        count,
        orderStatus,
        paymentId,
        deliveryId,
    )
}
