package com.blackfriday.payment.service

import com.blackfriday.payment.entity.Payment
import com.blackfriday.payment.entity.PaymentMethod
import com.blackfriday.payment.enums.PaymentMethodType
import com.blackfriday.payment.enums.PaymentStatus
import com.blackfriday.payment.pg.CreditCardPaymentAdapter
import com.blackfriday.payment.repository.PaymentMethodRepository
import com.blackfriday.payment.repository.PaymentRepository
import org.springframework.stereotype.Service

@Service
class PaymentService(
    private val paymentRepository: PaymentRepository,
    private val paymentMethodRepository: PaymentMethodRepository,
    private val creditCardPaymentAdapter: CreditCardPaymentAdapter,
) {
    fun registerPaymentMethod(
        userId: Long,
        paymentMethodType: PaymentMethodType,
        creditCardNumber: String,
    ): PaymentMethod {
        val paymentMethod = PaymentMethod(userId, paymentMethodType, creditCardNumber)

        return paymentMethodRepository.save(paymentMethod)
    }

    fun processPayment(
        userId: Long,
        orderId: Long,
        amountKRW: Long,
        paymentMethodId: Long,
    ): Payment {
        val paymentMethod = paymentMethodRepository.findById(paymentMethodId)
            .orElseThrow()

        if (paymentMethod.paymentMethodType != PaymentMethodType.CREDIT_CARD) {
            throw Exception("Unsupported payment method type")
        }

        val refCode =
            creditCardPaymentAdapter.processCreditCardPayment(amountKRW, paymentMethod.creditCardNumber)

        val payment = Payment(
            userId,
            orderId,
            amountKRW,
            paymentMethod.paymentMethodType,
            paymentMethod.creditCardNumber,
            PaymentStatus.COMPLETED,
            refCode
        )

        return paymentRepository.save(payment)
    }

    fun getPaymentMethod(userId: Long): PaymentMethod {
        return paymentMethodRepository.findByUserId(userId)
            .firstOrNull() ?: throw NoSuchElementException("User has no payment method")
    }

    fun getPayment(paymentId: Long): Payment {
        return paymentRepository.findById(paymentId).orElseThrow()
    }
}