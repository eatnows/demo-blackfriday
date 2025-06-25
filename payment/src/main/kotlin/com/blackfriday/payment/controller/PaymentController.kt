package com.blackfriday.payment.controller

import com.blackfriday.payment.dto.PaymentMethodDto
import com.blackfriday.payment.dto.ProcessPaymentDto
import com.blackfriday.payment.entity.Payment
import com.blackfriday.payment.entity.PaymentMethod
import com.blackfriday.payment.service.PaymentService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PaymentController(
    private val paymentService: PaymentService,
) {
    @PostMapping("/payment/methods")
    fun registerPaymentMethod(
        @RequestBody dto: PaymentMethodDto,
    ): PaymentMethod {
        return paymentService.registerPaymentMethod(
            dto.userId,
            dto.paymentMethodType,
            dto.creditCardNumber,
        )
    }

    @PostMapping("/payment/process-payment")
    fun processPayment(
        @RequestBody dto: ProcessPaymentDto,
    ): Payment {
        return paymentService.processPayment(
            dto.userId,
            dto.orderId,
            dto.amountKRW,
            dto.paymentMethodId,
        )
    }

    @GetMapping("/payment/users/{userId}/first-method")
    fun getPaymentMethod(
        @PathVariable userId: Long,
    ): PaymentMethod {
        return paymentService.getPaymentMethod(userId)
    }

    @GetMapping("/payment/payments/{paymentId}")
    fun getPayment(
        @PathVariable paymentId: Long,
    ): Payment {
        return paymentService.getPayment(paymentId)
    }
}