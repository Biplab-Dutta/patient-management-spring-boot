package com.zoroxnekko.billingservice.grpc

import io.grpc.stub.StreamObserver
import org.slf4j.LoggerFactory
import org.springframework.grpc.server.service.GrpcService

@GrpcService
class BillingGrpcService : BillingServiceGrpc.BillingServiceImplBase() {
    private val log = LoggerFactory.getLogger(this.javaClass)

    override fun createBillingAccount(
        request: BillingRequest,
        responseObserver: StreamObserver<BillingResponse>
    ) {
        log.info("createBillingAccount request received ${request.toString()}")

        // Perform some business logic below

        val response = BillingResponse.newBuilder()
            .setAccountId("233")
            .setStatus("ACTIVE")
            .build()

        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }
}