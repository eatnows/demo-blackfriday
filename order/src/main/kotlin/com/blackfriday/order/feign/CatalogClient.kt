package com.blackfriday.order.feign

import com.blackfriday.order.dto.DecreaseStockCountDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "catalogClient", url = "http://catalog-service:8080")
interface CatalogClient {
    @GetMapping("/catalog/products/{productId}")
    fun getProduct(@PathVariable productId: Long): Map<String, Any>

    @PostMapping("/catalog/products/{productId}/decreaseStockCount")
    fun decreaseStockCount(@PathVariable productId: Long, @RequestBody dto: DecreaseStockCountDto)
}