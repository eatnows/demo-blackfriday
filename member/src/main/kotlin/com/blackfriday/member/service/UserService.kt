package com.blackfriday.member.service

import com.blackfriday.member.entity.UserEntity
import com.blackfriday.member.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    fun registerUser(loginId: String, userName: String): UserEntity {
        val user = UserEntity(loginId, userName)
        return userRepository.save(user)
    }

    fun modifyUser(userId: Long, userName: String): UserEntity {
        var user = userRepository.findById(userId).orElseThrow()
        user.userName = userName

        return userRepository.save(user)
    }

    fun getUser(loginId: String): UserEntity {
        return userRepository.findByLoginId(loginId)
            ?: throw NoSuchElementException("User with loginId $loginId not found")
    }
}