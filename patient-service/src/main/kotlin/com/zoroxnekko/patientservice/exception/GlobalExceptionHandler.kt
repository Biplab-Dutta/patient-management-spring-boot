package com.zoroxnekko.patientservice.exception

import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {
    private val log = LoggerFactory.getLogger(this.javaClass)

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationException(ex: MethodArgumentNotValidException): ResponseEntity<Map<String, String>> {
        val errors = ex.bindingResult.fieldErrors
            .associate { error -> error.field to (error.defaultMessage ?: "") }
        return ResponseEntity.badRequest().body(errors)
    }

    @ExceptionHandler(EmailAlreadyExistsException::class)
    fun handleEmailAlreadyExistsException(ex: EmailAlreadyExistsException): ResponseEntity<Map<String, String>> {
        log.warn(ex.message)
        val error = mapOf("message" to "Email address already exists")
        return ResponseEntity.badRequest().body(error)
    }

    @ExceptionHandler(PatientNotFoundException::class)
    fun handlePatientNotFoundException(ex: PatientNotFoundException): ResponseEntity<Map<String, String>> {
        log.warn(ex.message)
        val error = mapOf("message" to "Patient not found")
        return ResponseEntity.badRequest().body(error)
    }
}