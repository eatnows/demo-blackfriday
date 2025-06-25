package com.blackfriday.payment.pg

import org.springframework.stereotype.Service
import kotlin.math.roundToLong

@Service
class EasyCreditCardPaymentAdapter : CreditCardPaymentAdapter {
    override fun processCreditCardPayment(amountKRW: Long, creditCardNumber: String): Long {
        // 실제 처리

        return (Math.random() * 10000000000).roundToLong()
    }
}