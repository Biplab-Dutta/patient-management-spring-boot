package com.zoroxnekko.patientservice.controller

import com.zoroxnekko.patientservice.dto.PatientRequestDTO
import com.zoroxnekko.patientservice.dto.PatientResponseDTO
import com.zoroxnekko.patientservice.service.PatientService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/patients")
class PatientController(
    private val service: PatientService,
) {
    @GetMapping
    fun getPatients(): ResponseEntity<List<PatientResponseDTO>> {
        val patients = service.getPatients()
        return ResponseEntity.ok().body(patients)
    }

    @PostMapping
    fun createPatient(@Valid @RequestBody patientRequestDTO: PatientRequestDTO): ResponseEntity<PatientResponseDTO> {
        val patientResponseDTO = service.createPatient(patientRequestDTO)
        return ResponseEntity.ok().body(patientResponseDTO)
    }
}