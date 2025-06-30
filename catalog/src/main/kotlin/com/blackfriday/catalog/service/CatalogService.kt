package com.blackfriday.catalog.service

import com.blackfriday.catalog.cassandra.entity.Product
import com.blackfriday.catalog.cassandra.repository.ProductRepository
import com.blackfriday.catalog.dto.ProductTagDto
import com.blackfriday.catalog.feign.SearchClient
import com.blackfriday.catalog.mysql.entity.SellerProduct
import com.blackfriday.catalog.mysql.repository.SellerProductRepository
import org.springframework.stereotype.Service

@Service
class CatalogService(
    private val sellerProductRepository: SellerProductRepository,
    private val productRepository: ProductRepository,
    private val searchClient: SearchClient,
) {
    fun registerProduct(
        sellerId: Long,
        name: String,
        description: String,
        price: Long,
        stockCount: Long,
        tags: List<String>
    ): Product {
        val sellerProduct = SellerProduct(sellerId)
        sellerProductRepository.save(sellerProduct)

        val product = Product(
            sellerProduct.id!!,
            sellerId,
            name,
            description,
            price,
            stockCount,
            tags
        )

        val dto = ProductTagDto(
            product.id,
            tags,
        )
        searchClient.addTagCache(dto)

        return productRepository.save(product)
    }

    fun deleteProduct(productId: Long) {
        val product = productRepository.findById(productId).orElseThrow()

        val dto = ProductTagDto(
            product.id,
            product.tags,
        )
        searchClient.removeTagCache(dto)

        productRepository.deleteById(productId)
        sellerProductRepository.deleteById(productId)
    }

    fun getProductsBySellerId(sellerId: Long): List<Product> {
        val sellerProducts = sellerProductRepository.findAllBySellerId(sellerId)

        val products = ArrayList<Product>()

        for (item in sellerProducts) {
            val product = productRepository.findById(item.id!!)
            product.ifPresent { products.add(it) }
        }

        return products
    }

    fun getProductById(productId: Long): Product {
        return productRepository.findById(productId).orElseThrow()
    }

    fun decreaseStockCount(productId: Long, decreaseCount: Long): Product {
        val product = productRepository.findById(productId).orElseThrow()
        product.stockCount -= decreaseCount

        return productRepository.save(product)
    }
}