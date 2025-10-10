package com.zoroxnekko.patientservice.kafka

import com.zoroxnekko.patientevents.kafka.PatientEvent
import com.zoroxnekko.patientservice.model.Patient
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class KafkaProducer(
    private val kafkaTemplate: KafkaTemplate<String, ByteArray>
) {
    private val log = LoggerFactory.getLogger(this.javaClass)

    fun sendEvent(patient: Patient) {
        val event = PatientEvent.newBuilder()
            .setPatientId(patient.id.toString())
            .setName(patient.name)
            .setEmail(patient.email)
            .setEventType("PATIENT_CREATED")
            .build()

        try {
            kafkaTemplate.send("patient", event.toByteArray())
            log.info("Patient event dispatched: $event")
        } catch (_: Exception) {
            log.error("Error sending PatientCreated event: $event")
        }
    }
}