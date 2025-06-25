package com.blackfriday.payment.dto

data class ProcessPaymentDto(
    val userId: Long,
    val orderId: Long,
    val amountKRW: Long,
    val paymentMethodId: Long,
)
