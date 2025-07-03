package com.blackfriday.order.dto

import com.blackfriday.order.enums.OrderStatus

data class ProductOrderDetailDto(
    val id: Long,

    val userId: Long,
    val productId: Long,

    val paymentId: Long,
    val deliveryId: Long,
    val orderStatus: OrderStatus,

    val paymentStatus: String,
    val deliveryStatus: String,
) {

}