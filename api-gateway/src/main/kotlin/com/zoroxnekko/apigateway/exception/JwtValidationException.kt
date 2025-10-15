package com.zoroxnekko.apigateway.exception

import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.reactive.function.client.WebClientResponseException
import org.springframework.web.server.ServerWebExchange

@RestControllerAdvice
class JwtValidationException {
    @ExceptionHandler(WebClientResponseException.Unauthorized::class)
    suspend fun handleUnauthorizedException(exchange: ServerWebExchange) {
        exchange.response.statusCode = HttpStatus.UNAUTHORIZED
        exchange.response.setComplete().awaitSingleOrNull()
    }
}
