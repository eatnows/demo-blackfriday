package com.blackfriday.catalog.mysql.repository

import com.blackfriday.catalog.mysql.entity.SellerProduct
import org.springframework.data.jpa.repository.JpaRepository

interface SellerProductRepository : JpaRepository<SellerProduct, Long> {
    fun findAllBySellerId(sellerId: Long): List<SellerProduct>
}