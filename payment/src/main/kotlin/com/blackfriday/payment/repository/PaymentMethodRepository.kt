package com.blackfriday.payment.repository

import com.blackfriday.payment.entity.PaymentMethod
import org.springframework.data.jpa.repository.JpaRepository

interface PaymentMethodRepository : JpaRepository<PaymentMethod, Long> {
    fun findByUserId(userId: Long): List<PaymentMethod>
}