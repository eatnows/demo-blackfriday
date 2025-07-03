package com.blackfriday.order.dto

data class StartOrderResponseDto(
    val orderId: Long,
    val paymentMethod: Map<String, Any>,
    val address: Map<String, Any>,
)
