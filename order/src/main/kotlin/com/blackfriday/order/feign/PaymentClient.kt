package com.blackfriday.order.feign

import com.blackfriday.order.dto.DecreaseStockCountDto
import com.blackfriday.order.dto.ProcessDeliveryDto
import com.blackfriday.order.dto.ProcessPaymentDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "paymentClient", url = "http://payment-service:8080")
interface PaymentClient {
    @GetMapping("/payment/users/{userId}/first-method")
    fun getPaymentMethod(@PathVariable userId: Long): Map<String, Any>

    @PostMapping("/payment/process-payment")
    fun processPayment(@RequestBody dto: ProcessPaymentDto): Map<String, Any>

    @GetMapping("/payment/payments/{paymentId}")
    fun getPayment(@PathVariable paymentId: Long): Map<String, Any>
}