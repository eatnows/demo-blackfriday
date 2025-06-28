package com.blackfriday.delivery.dg

interface DeliveryAdapter {
    fun processDelivery(productName: String, productCount: Long, address: String): Long
}