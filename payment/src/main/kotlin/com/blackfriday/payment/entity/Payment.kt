package com.blackfriday.payment.entity

import com.blackfriday.payment.enums.PaymentMethodType
import com.blackfriday.payment.enums.PaymentStatus
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.Table

@Entity
@Table(indexes = [Index(name = "idx_userId", columnList = "userId")])
data class Payment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    val userId: Long,

    @Column(unique = true)
    val orderId: Long,

    val amountKRW: Long,

    val paymentMethodType: PaymentMethodType,

    val paymentData: String, // credit card 번호

    val paymentStatus: PaymentStatus,

    @Column(unique = true)
    val referenceCode: Long,
) {
    constructor(
        userId: Long,
        orderId: Long,
        amountKRW: Long,
        paymentMethodType: PaymentMethodType,
        paymentData: String,
        paymentStatus: PaymentStatus,
        referenceCode: Long,
    ) : this(
        null,
        userId,
        orderId,
        amountKRW,
        paymentMethodType,
        paymentData,
        paymentStatus,
        referenceCode,
    )
}
