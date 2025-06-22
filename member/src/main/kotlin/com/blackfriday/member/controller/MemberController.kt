package com.blackfriday.member.controller

import com.blackfriday.member.dto.ModifyUserDto
import com.blackfriday.member.dto.RegisterUserDto
import com.blackfriday.member.entity.UserEntity
import com.blackfriday.member.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberController(
    private val userService: UserService,
) {
    @PostMapping("/member/users/registration")
    fun registerUser(@RequestBody dto: RegisterUserDto): UserEntity {
        return userService.registerUser(dto.loginId, dto.userName)
    }

    @PutMapping("/member/users/{userId}/modify")
    fun modifyUser(@PathVariable userId: Long, @RequestBody dto: ModifyUserDto): UserEntity {
        return userService.modifyUser(userId, dto.userName)
    }

    @PostMapping("/member/users/{loginId}/login")
    fun login(@PathVariable loginId: String): UserEntity {
        return userService.getUser(loginId)
    }
}