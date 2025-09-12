package com.zoroxnekko.patientservice.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull
import java.time.LocalDate

@Entity
class Patient(
    @Id @GeneratedValue
    var id: Long? = null,

    @field:NotNull
    var name: String,

    @field:NotNull
    @field:Email
    @Column(unique = true)
    var email: String,

    @field:NotNull
    var address: String,

    @field:NotNull
    var dateOfBirth: LocalDate,

    @field:NotNull
    var registeredDate: LocalDate
)