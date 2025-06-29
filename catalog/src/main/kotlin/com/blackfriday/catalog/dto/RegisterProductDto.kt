package com.blackfriday.catalog.dto

data class RegisterProductDto(
    val sellerId: Long,
    val name: String,
    val description: String,
    val price: Long,
    val stackCount: Long,
    val tags: List<String>,
)
