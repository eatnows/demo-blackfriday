package com.blackfriday.catalog.dto

data class ProductTagDto(
    val productId: Long,
    val tags: List<String>,
)
