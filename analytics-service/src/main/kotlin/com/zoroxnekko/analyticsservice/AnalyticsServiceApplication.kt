package com.zoroxnekko.analyticsservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.kafka.annotation.EnableKafka

@SpringBootApplication
@EnableKafka
class AnalyticsServiceApplication

fun main(args: Array<String>) {
    runApplication<AnalyticsServiceApplication>(*args)
}
