package com.blackfriday.delivery.repository

import com.blackfriday.delivery.entity.Delivery
import com.blackfriday.delivery.enums.DeliveryStatus
import org.springframework.data.jpa.repository.JpaRepository

interface DeliveryRepository : JpaRepository<Delivery, Long> {
    fun findAllByOrderId(orderId: Long): List<Delivery>
    fun findAllByDeliveryStatus(deliveryStatus: DeliveryStatus): List<Delivery>
}