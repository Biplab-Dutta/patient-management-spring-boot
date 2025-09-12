package com.zoroxnekko.patientservice.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class PatientRequestDTO(
    @field:NotBlank(message = "Name is required")
    @field:Size(max = 100, message = "Name cannot exceed 100 characters")
    val name: String,

    @field:NotBlank(message = "Email is required")
    @field:Email(message = "Email should be valid")
    val email: String,

    @field:NotBlank(message = "Address is required")
    val address: String,

    @field:NotBlank(message = "Date of birth is required")
    val dateOfBirth: String,

    @field:NotBlank(message = "Registered date is required")
    val registeredDate: String,
)