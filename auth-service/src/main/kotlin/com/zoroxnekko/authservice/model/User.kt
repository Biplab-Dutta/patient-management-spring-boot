package com.zoroxnekko.authservice.model

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import java.util.*

@Entity
@Table(name = "users")
class User(
    @Id @GeneratedValue
    var id: UUID? = null,

    @field:NotBlank
    @field:Email
    @Column(unique = true, nullable = false)
    var email: String,

    @Column(nullable = false)
    @field:NotBlank
    var password: String,

    @Column(nullable = false)
    var role: String,
)