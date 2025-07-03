package com.blackfriday.order.dto

data class StartOrderDto(
    val userId: Long,
    val productId: Long,
    val count: Long,
)
