package com.zoroxnekko.analyticsservice.kafka

import com.google.protobuf.InvalidProtocolBufferException
import com.zoroxnekko.patientevents.kafka.PatientEvent
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class KafkaConsumer {
    private val log = LoggerFactory.getLogger(this.javaClass)

    @KafkaListener(topics = ["patient"], groupId = "analytics-service")
    fun consumeEvent(event: ByteArray) {
        try {
            val patientEvent = PatientEvent.parseFrom(event)
            // Perform any business related to analytics here
            log.info("Received patient event: $patientEvent")
        } catch (e: InvalidProtocolBufferException) {
            log.error("Error deserializing patient event: ${e.message}")
        }
    }
}