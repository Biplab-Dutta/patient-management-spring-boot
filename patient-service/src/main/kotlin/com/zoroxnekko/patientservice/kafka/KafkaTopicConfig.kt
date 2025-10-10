package com.zoroxnekko.patientservice.kafka

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class KafkaTopicConfig {
    @Bean
    fun patientTopic(): NewTopic {
        return NewTopic("patient", 1, 1.toShort())
    }
}