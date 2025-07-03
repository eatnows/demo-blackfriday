package com.blackfriday.order.dto

data class FinalOrderDto(
    val orderId: Long,
    val paymentMethodId: Long,
    val addressId: Long,
)
