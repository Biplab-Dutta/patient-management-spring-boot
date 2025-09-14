package com.zoroxnekko.patientservice.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDate
import java.util.*

@Entity
class Patient(
    @Id @GeneratedValue
    var id: UUID? = null,

    @field:NotBlank
    var name: String,

    @field:NotBlank
    @field:Email
    @Column(unique = true)
    var email: String,

    @field:NotBlank
    var address: String,

    @field:NotNull
    var dateOfBirth: LocalDate,

    @field:NotNull
    var registeredDate: LocalDate
)