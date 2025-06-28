package com.blackfriday.delivery.dto

data class RegisterDeliveryDto(
    val userId: Long,
    val address: String,
    val alias: String,
)
