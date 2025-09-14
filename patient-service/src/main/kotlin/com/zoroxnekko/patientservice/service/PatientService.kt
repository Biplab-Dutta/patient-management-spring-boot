package com.zoroxnekko.patientservice.service

import com.zoroxnekko.patientservice.dto.PatientRequestDTO
import com.zoroxnekko.patientservice.dto.PatientResponseDTO
import com.zoroxnekko.patientservice.exception.EmailAlreadyExistsException
import com.zoroxnekko.patientservice.exception.PatientNotFoundException
import com.zoroxnekko.patientservice.mapper.toDTO
import com.zoroxnekko.patientservice.mapper.toEntity
import com.zoroxnekko.patientservice.repository.PatientRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*

@Service
class PatientService(
    private val repository: PatientRepository,
) {
    fun getPatients(): List<PatientResponseDTO> {
        val patients = repository.findAll()
        return patients.map { it.toDTO() }
    }

    fun createPatient(patientRequestDTO: PatientRequestDTO): PatientResponseDTO {
        if (repository.existsByEmail(patientRequestDTO.email!!)) {
            throw EmailAlreadyExistsException("A patient with the email ${patientRequestDTO.email} already exists")
        }

        val patient = repository.save(patientRequestDTO.toEntity())
        return patient.toDTO()
    }

    fun updatePatient(id: UUID, patientRequestDTO: PatientRequestDTO): PatientResponseDTO {
        val patient = repository.findById(id).orElseThrow {
            PatientNotFoundException("Patient not found with id $id")
        }

        if (repository.existsByEmailAndIdNot(email = patientRequestDTO.email!!, id = id)) {
            throw EmailAlreadyExistsException("A patient with the email ${patientRequestDTO.email} already exists")
        }

        patient.name = patientRequestDTO.name!!
        patient.email = patientRequestDTO.email
        patient.address = patientRequestDTO.address!!
        patient.dateOfBirth = LocalDate.parse(patientRequestDTO.dateOfBirth!!)

        val updatedPatient = repository.save(patient)
        return updatedPatient.toDTO()
    }
}