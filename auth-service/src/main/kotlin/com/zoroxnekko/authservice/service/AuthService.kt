package com.zoroxnekko.authservice.service

import com.zoroxnekko.authservice.dto.LoginRequestDTO
import com.zoroxnekko.authservice.util.JwtUtil
import io.jsonwebtoken.JwtException
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userService: UserService,
    private val passwordEncoder: PasswordEncoder,
    private val jwtUtil: JwtUtil,
) {
    private val log = LoggerFactory.getLogger(this.javaClass)
    fun authenticate(loginRequestDTO: LoginRequestDTO): String? {
        val user = userService.findByEmail(loginRequestDTO.email)
        return user?.takeIf { passwordEncoder.matches(loginRequestDTO.password, it.password) }?.let {
            log.info("This is user: ${it.toString()}")
            jwtUtil.generateToken(it.email, it.role)
        }
    }

    fun validateToken(token: String): Boolean {
        return try {
            jwtUtil.validateToken(token)
            true
        } catch (e: JwtException) {
            false
        }
    }
}