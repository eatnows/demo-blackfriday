package com.blackfriday.delivery.dg

import org.springframework.stereotype.Service

@Service
class FastDeliveryAdapter : DeliveryAdapter {
    override fun processDelivery(productName: String, productCount: Long, address: String): Long {
        // delivery process

        return 11111111
    }
}