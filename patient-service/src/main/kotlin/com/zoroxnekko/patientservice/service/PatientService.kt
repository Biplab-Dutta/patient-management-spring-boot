package com.zoroxnekko.patientservice.service

import com.zoroxnekko.patientservice.dto.PatientResponseDTO
import com.zoroxnekko.patientservice.mapper.toDTO
import com.zoroxnekko.patientservice.repository.PatientRepository
import org.springframework.stereotype.Service

@Service
class PatientService(
    private val repository: PatientRepository,
) {
    fun getPatients(): List<PatientResponseDTO> {
        val patients = repository.findAll()
        return patients.map { it.toDTO() }
    }
}