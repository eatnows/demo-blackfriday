package com.blackfriday.order.feign

import com.blackfriday.order.dto.DecreaseStockCountDto
import com.blackfriday.order.dto.ProcessDeliveryDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "deliveryClient", url = "http://delivery-service:8080")
interface DeliveryClient {
    @GetMapping("/delivery/users/{userId}/first-address")
    fun getUserAddress(@PathVariable userId: Long): Map<String, Any>

    @GetMapping("/delivery/address/{addressId}")
    fun getAddress(@PathVariable addressId: Long): Map<String, Any>

    @PostMapping("/delivery/process-delivery")
    fun processDelivery(@RequestBody dto: ProcessDeliveryDto): Map<String, Any>

    @GetMapping("/delivery/deliveries/{deliveryId}")
    fun getDelivery(@PathVariable deliveryId: Long): Map<String, Any>
}