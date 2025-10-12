package com.zoroxnekko.authservice.util

import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.security.SignatureException
import java.util.Base64
import java.util.Date
import javax.crypto.SecretKey

@Component
class JwtUtil(
    @Value("\${jwt.secret}") secret: String
) {
    private val secretKey: SecretKey

    init {
        val keyBytes = Base64.getDecoder().decode(secret.toByteArray(StandardCharsets.UTF_8))
        secretKey = Keys.hmacShaKeyFor(keyBytes)
    }

    fun generateToken(email: String, role: String): String =
        Jwts.builder()
            .subject(email)
            .claim("role", role)
            .issuedAt(Date())
            .expiration(Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
            .signWith(secretKey)
            .compact()

    fun validateToken(token: String) {
        try {
            Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
        } catch (e: SignatureException) {
            throw JwtException("Invalid JWT signature", e)
        } catch (e: JwtException) {
            throw JwtException("Invalid JWT", e)
        }
    }
}
