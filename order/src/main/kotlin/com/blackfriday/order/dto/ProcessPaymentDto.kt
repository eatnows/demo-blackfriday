package com.blackfriday.order.dto

data class ProcessPaymentDto(
    val orderId: Long,
    val userId: Long,
    val amountKRW: Long,
    val paymentMethodId: Long,
)
