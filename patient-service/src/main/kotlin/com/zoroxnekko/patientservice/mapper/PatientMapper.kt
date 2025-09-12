package com.zoroxnekko.patientservice.mapper

import com.zoroxnekko.patientservice.dto.PatientResponseDTO
import com.zoroxnekko.patientservice.model.Patient

fun Patient.toDTO(): PatientResponseDTO {
    return PatientResponseDTO(
        id = id?.toString()!!,
        name = name,
        email = email,
        address = address,
        dateOfBirth = dateOfBirth.toString(),
    )
}