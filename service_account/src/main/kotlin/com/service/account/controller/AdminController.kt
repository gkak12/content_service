package com.service.account.controller

import com.service.account.GrpcAdminRequest
import com.service.account.GrpcAdminResponse
import com.service.account.GrpcAdminServiceGrpc
import com.service.account.service.AdminService
import io.grpc.stub.StreamObserver
import net.devh.boot.grpc.server.service.GrpcService
import org.slf4j.LoggerFactory

@GrpcService
class AdminController(
    private val adminService: AdminService
): GrpcAdminServiceGrpc.GrpcAdminServiceImplBase() {

    private val logger = LoggerFactory.getLogger(AdminController::class.java)

    override fun findAdminByName(request: GrpcAdminRequest, responseObserver: StreamObserver<GrpcAdminResponse>) {
        logger.info("AdminController findAdmin request: $request")

        responseObserver.onNext(adminService.findAdminByName(request.adminName))
        responseObserver.onCompleted()
    }
}