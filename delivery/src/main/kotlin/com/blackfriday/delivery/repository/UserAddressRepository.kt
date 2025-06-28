package com.blackfriday.delivery.repository

import com.blackfriday.delivery.entity.UserAddress
import org.springframework.data.jpa.repository.JpaRepository

interface UserAddressRepository : JpaRepository<UserAddress, Long> {
    fun findByUserId(userId: Long): List<UserAddress>
}