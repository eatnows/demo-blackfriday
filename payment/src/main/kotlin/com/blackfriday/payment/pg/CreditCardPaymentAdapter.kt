package com.blackfriday.payment.pg

interface CreditCardPaymentAdapter {
    fun processCreditCardPayment(amountKRW: Long, creditCardNumber: String): Long
}