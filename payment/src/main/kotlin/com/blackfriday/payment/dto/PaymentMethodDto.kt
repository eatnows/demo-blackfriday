package com.blackfriday.payment.dto

import com.blackfriday.payment.enums.PaymentMethodType

data class PaymentMethodDto(
    val userId: Long,
    val paymentMethodType: PaymentMethodType,
    val creditCardNumber: String,
)
