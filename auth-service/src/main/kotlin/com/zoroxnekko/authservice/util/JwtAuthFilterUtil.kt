package com.zoroxnekko.authservice.util

import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthFilterUtil(
    private val jwtUtil: JwtUtil
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val authHeader = request.getHeader("Authorization")

        if (authHeader != null && authHeader.startsWith("Bearer ") &&
            SecurityContextHolder.getContext().authentication == null
        ) {
            val token = authHeader.substringAfter("Bearer ")
            try {
                jwtUtil.validateToken(token)

                val claims = Jwts.parser()
                    .verifyWith(jwtUtil.getSecretKey())
                    .build()
                    .parseSignedClaims(token)
                    .payload

                val email = claims.subject
                val role = claims["role"] as String

                val auth = UsernamePasswordAuthenticationToken(
                    email,
                    null,
                    listOf(SimpleGrantedAuthority(role))
                )
                SecurityContextHolder.getContext().authentication = auth
            } catch (e: JwtException) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.message)
                return
            }
        }

        filterChain.doFilter(request, response)
    }
}
