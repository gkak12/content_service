package com.service.account.controller

import com.service.account.GrpcAdminProtoDto
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

    override fun login(protoDto: GrpcAdminProtoDto, responseObserver: StreamObserver<GrpcAdminResponse>) {
        logger.info("AdminController login protoDto: ${protoDto.adminId}")

        responseObserver.onNext(adminService.login(protoDto))
        responseObserver.onCompleted()
    }

    override fun signup(protoDto: GrpcAdminProtoDto, responseObserver: StreamObserver<GrpcAdminResponse>) {
        logger.info("AdminController signup protoDto: ${protoDto.adminId}")

        responseObserver.onNext(adminService.signup(protoDto))
        responseObserver.onCompleted()
    }

    override fun findAdminByName(request: GrpcAdminRequest, responseObserver: StreamObserver<GrpcAdminResponse>) {
        logger.info("AdminController findAdminByName request: $request")

        responseObserver.onNext(adminService.findAdminByName(request))
        responseObserver.onCompleted()
    }
}