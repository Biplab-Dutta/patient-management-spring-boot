package com.zoroxnekko.billingservice.grpc

import com.zoroxnekko.billing.grpc.BillingRequest
import com.zoroxnekko.billing.grpc.BillingResponse
import com.zoroxnekko.billing.grpc.BillingServiceGrpcKt
import org.slf4j.LoggerFactory
import org.springframework.grpc.server.service.GrpcService

@GrpcService
class BillingGrpcService : BillingServiceGrpcKt.BillingServiceCoroutineImplBase() {
    private val log = LoggerFactory.getLogger(this.javaClass)

    override suspend fun createBillingAccount(request: BillingRequest): BillingResponse {
        log.info("createBillingAccount request received $request")

        return BillingResponse.newBuilder()
            .setAccountId("233")
            .setStatus("ACTIVE")
            .build()
    }
}