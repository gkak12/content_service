package com.service.account.controller

import com.service.account.GrpcUserProtoDto
import com.service.account.GrpcUserRequest
import com.service.account.GrpcUserResponse
import com.service.account.GrpcUserServiceGrpc
import com.service.account.service.UserService
import io.grpc.stub.StreamObserver
import net.devh.boot.grpc.server.service.GrpcService
import org.slf4j.LoggerFactory

@GrpcService
class UserController(
    private val userService: UserService
): GrpcUserServiceGrpc.GrpcUserServiceImplBase() {

    private val logger = LoggerFactory.getLogger(UserController::class.java)

    override fun findUserById(request: GrpcUserRequest, responseObserver: StreamObserver<GrpcUserResponse>) {
        logger.info("UserController findUserById request: $request")

        responseObserver.onNext(userService.findUserById(request))
    }

    override fun findUserByName(request: GrpcUserRequest, responseObserver: StreamObserver<GrpcUserResponse>) {
        logger.info("UserController findUserByName request: $request")

        responseObserver.onNext(userService.findUserByName(request))
        responseObserver.onCompleted()
    }

    override fun loginUser(protoDto: GrpcUserProtoDto, responseObserver: StreamObserver<GrpcUserResponse>) {
        logger.info("UserController loginUser protoDto: $protoDto")

        responseObserver.onNext(userService.loginUser(protoDto))
        responseObserver.onCompleted()
    }
}