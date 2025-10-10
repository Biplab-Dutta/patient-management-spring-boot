package com.zoroxnekko.patientservice.grpc

import com.zoroxnekko.billing.grpc.BillingRequest
import com.zoroxnekko.billing.grpc.BillingResponse
import com.zoroxnekko.billing.grpc.BillingServiceGrpcKt
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.Closeable
import java.util.concurrent.TimeUnit

@Service
class BillingServiceGrpcClient(
    @Value("\${billing.service.address:localhost}") serverAddress: String,
    @Value("\${billing.service.grpc.port:9001}") serverPort: Int
) : Closeable {
    private val log = LoggerFactory.getLogger(BillingServiceGrpcClient::class.java)

    init {
        log.info("Connecting to Billing gRPC service at $serverAddress:$serverPort")
    }

    private val channel: ManagedChannel by lazy {
        ManagedChannelBuilder
            .forAddress(serverAddress, serverPort)
            .usePlaintext()
            .build()
    }

    private val stub: BillingServiceGrpcKt.BillingServiceCoroutineStub by lazy {
        BillingServiceGrpcKt.BillingServiceCoroutineStub(channel)
    }


    suspend fun createBillingAccount(patientId: String, name: String, email: String): BillingResponse {
        val request = BillingRequest.newBuilder()
            .setPatientId(patientId)
            .setName(name)
            .setEmail(email)
            .build()

        val response = stub.createBillingAccount(request)
        log.info("Received response from billing service via gRPC: $response")
        return response
    }

    override fun close() {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS)
    }
}
