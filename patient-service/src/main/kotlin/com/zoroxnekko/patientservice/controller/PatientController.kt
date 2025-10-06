package com.zoroxnekko.patientservice.controller

import com.zoroxnekko.patientservice.dto.PatientRequestDTO
import com.zoroxnekko.patientservice.dto.PatientResponseDTO
import com.zoroxnekko.patientservice.dto.validators.CreatePatientValidationGroup
import com.zoroxnekko.patientservice.service.PatientService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.groups.Default
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/patients")
@Tag(name = "Patient", description = "API for managing patients")
class PatientController(
    private val service: PatientService,
) {
    @GetMapping
    @Operation(summary = "Get all patients")
    fun getPatients(): ResponseEntity<List<PatientResponseDTO>> {
        val patients = service.getPatients()
        return ResponseEntity.ok().body(patients)
    }

    @PostMapping
    @Operation(summary = "Create a new patient")
    suspend fun createPatient(
        @Validated(Default::class, CreatePatientValidationGroup::class)
        @RequestBody patientRequestDTO: PatientRequestDTO
    ): ResponseEntity<PatientResponseDTO> {
        val patientResponseDTO = service.createPatient(patientRequestDTO)
        return ResponseEntity.ok().body(patientResponseDTO)
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a patient")
    fun updatePatient(
        @PathVariable id: UUID,
        @Validated(Default::class) @RequestBody patientRequestDTO: PatientRequestDTO,
    ): ResponseEntity<PatientResponseDTO> {
        val patientResponseDTO = service.updatePatient(id, patientRequestDTO)
        return ResponseEntity.ok().body(patientResponseDTO)
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a patient")
    fun deletePatient(@PathVariable id: UUID): ResponseEntity<Unit> {
        service.deletePatient(id)
        return ResponseEntity.noContent().build()
    }
}