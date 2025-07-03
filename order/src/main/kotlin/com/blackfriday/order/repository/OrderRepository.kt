package com.blackfriday.order.repository

import com.blackfriday.order.entity.ProductOrder
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<ProductOrder, Long> {
    fun findAllByUserId(userId: Long): List<ProductOrder>
}