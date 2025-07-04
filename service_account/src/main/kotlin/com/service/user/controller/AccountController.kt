package com.service.user.controller

import com.service.account.GrpcAccountServiceGrpc
import net.devh.boot.grpc.server.service.GrpcService

@GrpcService
class AccountController(

) : GrpcAccountServiceGrpc.GrpcAccountServiceImplBase() {
}