package com.blackfriday.delivery.dg

import com.blackfriday.delivery.enums.DeliveryStatus
import com.blackfriday.delivery.repository.DeliveryRepository
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class DeliveryStatusUpdater(
    private val deliveryRepository: DeliveryRepository,
) {
    @Scheduled(fixedDelay = 10000)
    fun deliveryStatusUpdate() {
        val inDeliveryList = deliveryRepository.findAllByDeliveryStatus(DeliveryStatus.IN_DELIVERY)
        inDeliveryList.forEach {
            it.deliveryStatus = DeliveryStatus.COMPLETED
            deliveryRepository.save(it)
        }

        val requestedList = deliveryRepository.findAllByDeliveryStatus(DeliveryStatus.REQUESTED)
        requestedList.forEach {
            it.deliveryStatus = DeliveryStatus.IN_DELIVERY
            deliveryRepository.save(it)
        }
    }
}