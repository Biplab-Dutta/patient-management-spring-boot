package com.zoroxnekko.patientservice.mapper

import com.zoroxnekko.patientservice.dto.PatientRequestDTO
import com.zoroxnekko.patientservice.dto.PatientResponseDTO
import com.zoroxnekko.patientservice.model.Patient
import java.time.LocalDate

fun Patient.toDTO(): PatientResponseDTO {
    return PatientResponseDTO(
        id = id?.toString()!!,
        name = name,
        email = email,
        address = address,
        dateOfBirth = dateOfBirth.toString(),
    )
}

fun PatientRequestDTO.toEntity(): Patient {
    return Patient(
        name = name,
        email = email,
        address = address,
        dateOfBirth = LocalDate.parse(dateOfBirth),
        registeredDate = LocalDate.parse(registeredDate),
    )
}