package com.blackfriday.payment.entity

import com.blackfriday.payment.enums.PaymentMethodType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.Table

@Entity
@Table(indexes = [Index(name = "idx_userId", columnList = "userId")])
data class PaymentMethod(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    val userId: Long,

    val paymentMethodType: PaymentMethodType,

    val creditCardNumber: String,
) {
    constructor(
        userId: Long,
        paymentMethodType: PaymentMethodType,
        creditCardNumber: String,
    ) : this(
        null,
        userId,
        paymentMethodType,
        creditCardNumber
    )
}