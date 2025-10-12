package com.zoroxnekko.authservice.service

import com.zoroxnekko.authservice.dto.LoginRequestDTO
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userService: UserService,
    private val passwordEncoder: PasswordEncoder,
    private val jwtUtil: J,
) {
    fun authenticate(loginRequestDTO: LoginRequestDTO): String? {
       return userService.findByEmail(loginRequestDTO.email)

    }
}