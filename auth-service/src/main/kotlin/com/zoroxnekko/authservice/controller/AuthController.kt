package com.zoroxnekko.authservice.controller

import com.zoroxnekko.authservice.dto.LoginRequestDTO
import com.zoroxnekko.authservice.dto.LoginResponseDTO
import com.zoroxnekko.authservice.service.AuthService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
    private val authService: AuthService,
) {
    @Operation(summary = "Generate token on user login")
    @PostMapping("/login")
    fun login(@RequestBody loginRequestDTO: LoginRequestDTO): ResponseEntity<LoginResponseDTO> {
        val token = authService.authenticate(loginRequestDTO)
        return if (token == null) ResponseEntity.status(HttpStatus.UNAUTHORIZED).build() else
            ResponseEntity.ok(LoginResponseDTO(token))
    }

    @Operation(summary = "Validate token")
    @GetMapping("/validate")
    fun validateToken(@RequestHeader("Authorization") authHeader: String): ResponseEntity<Unit> {
        val token = authHeader.removePrefix("Bearer ").trim()

        if (!authHeader.startsWith("Bearer ") || token.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
        }

        return if (authService.validateToken(token) != null)
            ResponseEntity.ok().build()
        else
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
    }
}