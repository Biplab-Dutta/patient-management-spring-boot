package com.zoroxnekko.authservice.service

import com.zoroxnekko.authservice.model.User
import com.zoroxnekko.authservice.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    fun findByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }
}