package com.service.point.controller

import com.service.point.GrpcPointRequest
import com.service.point.GrpcPointResponse
import com.service.point.GrpcPointServiceGrpc
import com.service.point.service.PointService
import io.grpc.stub.StreamObserver
import net.devh.boot.grpc.server.service.GrpcService
import org.slf4j.LoggerFactory

@GrpcService
class PointController (
    private val pointService: PointService
): GrpcPointServiceGrpc.GrpcPointServiceImplBase() {

    private val logger = LoggerFactory.getLogger(PointController::class.java)

    override fun findPointById(request: GrpcPointRequest, responseObserver: StreamObserver<GrpcPointResponse>) {
        logger.info("PointController findPointById request: ${request}")

        responseObserver.onNext(pointService.findPointById(request))
        responseObserver.onCompleted()
    }
}