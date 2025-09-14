package com.zoroxnekko.patientservice.controller

import com.zoroxnekko.patientservice.dto.PatientRequestDTO
import com.zoroxnekko.patientservice.dto.PatientResponseDTO
import com.zoroxnekko.patientservice.dto.validators.CreatePatientValidationGroup
import com.zoroxnekko.patientservice.service.PatientService
import jakarta.validation.groups.Default
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.util.*

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
    fun createPatient(
        @Validated(Default::class, CreatePatientValidationGroup::class)
        @RequestBody patientRequestDTO: PatientRequestDTO
    ): ResponseEntity<PatientResponseDTO> {
        val patientResponseDTO = service.createPatient(patientRequestDTO)
        return ResponseEntity.ok().body(patientResponseDTO)
    }

    @PutMapping("/{id}")
    fun updatePatient(
        @PathVariable id: UUID,
        @Validated(Default::class) @RequestBody patientRequestDTO: PatientRequestDTO,
    ): ResponseEntity<PatientResponseDTO> {
        val patientResponseDTO = service.updatePatient(id, patientRequestDTO)
        return ResponseEntity.ok().body(patientResponseDTO)
    }

    @DeleteMapping("/{id}")
    fun deletePatient(@PathVariable id: UUID): ResponseEntity<Unit> {
        service.deletePatient(id)
        return ResponseEntity.noContent().build()
    }
}