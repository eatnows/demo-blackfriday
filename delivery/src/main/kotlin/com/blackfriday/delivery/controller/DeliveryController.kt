package com.blackfriday.delivery.controller

import com.blackfriday.delivery.dto.ProcessDeliveryDto
import com.blackfriday.delivery.dto.RegisterDeliveryDto
import com.blackfriday.delivery.entity.Delivery
import com.blackfriday.delivery.entity.UserAddress
import com.blackfriday.delivery.service.DeliveryService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class DeliveryController(
    private val deliveryService: DeliveryService,
) {
    @PostMapping("/delivery/addresses")
    fun registerAddress(@RequestBody dto: RegisterDeliveryDto): UserAddress {
        return deliveryService.addUserAddress(
            dto.userId,
            dto.address,
            dto.alias,
        )
    }

    @PostMapping("/delivery/process-delivery")
    fun processDelivery(@RequestBody dto: ProcessDeliveryDto): Delivery {
        return deliveryService.processDelivery(
            dto.orderId,
            dto.productName,
            dto.productCount,
            dto.address,
        )
    }

    @GetMapping("/delivery/deliveries/{deliveryId}")
    fun getDelivery(
        @PathVariable deliveryId: Long,
    ): Delivery {
        return deliveryService.getDelivery(deliveryId)
    }

    @GetMapping("/delivery/addresses/{addressId}")
    fun getAddress(
        @PathVariable addressId: Long,
    ): UserAddress? {
        return deliveryService.getAddress(addressId)
    }

    @GetMapping("/delivery/users/{userId}/first-address")
    fun getUserAddress(
        @PathVariable userId: Long,
    ): UserAddress? {
        return deliveryService.getUserAddress(userId)
    }
}