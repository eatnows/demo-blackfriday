package com.blackfriday.delivery.service

import com.blackfriday.delivery.dg.DeliveryAdapter
import com.blackfriday.delivery.entity.Delivery
import com.blackfriday.delivery.entity.UserAddress
import com.blackfriday.delivery.enums.DeliveryStatus
import com.blackfriday.delivery.repository.DeliveryRepository
import com.blackfriday.delivery.repository.UserAddressRepository
import org.springframework.stereotype.Service

@Service
class DeliveryService(
    private val deliveryRepository: DeliveryRepository,
    private val userAddressRepository: UserAddressRepository,
    private val deliveryAdapter: DeliveryAdapter,
) {
    fun addUserAddress(userId: Long, address: String, alias: String): UserAddress {
        val userAddress = UserAddress(userId, address, alias)

        return userAddressRepository.save(userAddress)
    }

    fun processDelivery(
        orderId: Long,
        productName: String,
        productCount: Long,
        address: String
    ): Delivery {
        val refCode = deliveryAdapter.processDelivery(productName, productCount, address)

        val delivery = Delivery(
            orderId,
            productName,
            productCount,
            address,
            refCode,
            DeliveryStatus.REQUESTED
        )

        return deliveryRepository.save(delivery)
    }

    fun getDelivery(deliveryId: Long): Delivery {
        return deliveryRepository.findById(deliveryId).orElseThrow()
    }

    fun getAddress(addressId: Long): UserAddress? {
        return userAddressRepository.findById(addressId).orElseThrow()
    }

    fun getUserAddress(userId: Long): UserAddress {
        return userAddressRepository.findByUserId(userId).first()
    }
}