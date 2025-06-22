package com.blackfriday.member.repository

import com.blackfriday.member.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, Long> {
    fun findByLoginId(loginId: String): UserEntity?
}