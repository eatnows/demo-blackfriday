package com.blackfriday.search.dto

data class ProductTagsDto(
    val productId: Long,
    val tags: List<String>,
)