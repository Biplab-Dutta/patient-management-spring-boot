package com.zoroxnekko.patientservice.service

import com.zoroxnekko.patientservice.dto.PatientRequestDTO
import com.zoroxnekko.patientservice.dto.PatientResponseDTO
import com.zoroxnekko.patientservice.exception.EmailAlreadyExistsException
import com.zoroxnekko.patientservice.mapper.toDTO
import com.zoroxnekko.patientservice.mapper.toEntity
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

    fun createPatient(patientRequestDTO: PatientRequestDTO): PatientResponseDTO {
        if(repository.existsByEmail(patientRequestDTO.email)) {
           throw EmailAlreadyExistsException("A patient with the email ${patientRequestDTO.email} already exists")
        }

        val patient = repository.save(patientRequestDTO.toEntity())
        return patient.toDTO()
    }
}