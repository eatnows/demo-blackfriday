package com.blackfriday.catalog.controller

import com.blackfriday.catalog.cassandra.entity.Product
import com.blackfriday.catalog.dto.DecreaseStockCountDto
import com.blackfriday.catalog.dto.RegisterProductDto
import com.blackfriday.catalog.service.CatalogService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CatalogController(
    private val catalogService: CatalogService,
) {
    @PostMapping("/catalog/products")
    fun registerProduct(@RequestBody dto: RegisterProductDto): Product {
        return catalogService.registerProduct(
            dto.sellerId,
            dto.name,
            dto.description,
            dto.price,
            dto.stackCount,
            dto.tags
        )
    }

    @DeleteMapping("/catalog/products/{productId}")
    fun deleteProduct(@PathVariable productId: Long) {
        catalogService.deleteProduct(productId)
    }

    @GetMapping("/catalog/products/{productId}")
    fun getProductById(@PathVariable productId: Long): Product {
        return catalogService.getProductById(productId)
    }

    @GetMapping("/catalog/sellers/{sellerId}/products")
    fun getProductsBySellerId(@PathVariable sellerId: Long): List<Product> {
        return catalogService.getProductsBySellerId(sellerId)
    }

    @PostMapping("/catalog/products/{productId}/decreaseStockCount")
    fun decreaseStockCount(@PathVariable productId: Long, @RequestBody dto: DecreaseStockCountDto): Product {
        return catalogService.decreaseStockCount(productId, dto.decreaseCount)
    }
}