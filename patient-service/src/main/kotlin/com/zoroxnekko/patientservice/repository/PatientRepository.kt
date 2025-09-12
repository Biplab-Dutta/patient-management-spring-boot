package com.zoroxnekko.patientservice.repository

import com.zoroxnekko.patientservice.model.Patient
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface PatientRepository : JpaRepository<Patient, UUID>