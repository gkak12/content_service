package com.service.grpc.exception

import com.service.common.Response
import io.grpc.*
import io.grpc.protobuf.ProtoUtils
import org.apache.commons.lang3.StringUtils
import org.slf4j.LoggerFactory

class GrpcExceptionHandler : ServerInterceptor {

    private val log = LoggerFactory.getLogger(GrpcExceptionHandler::class.java)

    override fun <ReqT, ResT> interceptCall(
        call: ServerCall<ReqT, ResT>,
        headers: Metadata,
        next: ServerCallHandler<ReqT, ResT>
    ): ServerCall.Listener<ReqT> {

        val wrappedCall = object : ForwardingServerCall.SimpleForwardingServerCall<ReqT, ResT>(call) {
            override fun close(status: Status, trailers: Metadata) {
                val finalStatus = if (status.code != Status.Code.OK) {
                    handleException(status, trailers)
                } else status

                super.close(finalStatus, trailers)
            }
        }

        return ExceptionHandlingServerCallListener(
            next.startCall(wrappedCall, headers),
            wrappedCall,
            headers
        )
    }

    private fun handleException(status: Status, metadata: Metadata): Status {
        val errorResponse = Response.newBuilder()
            .setStatusCode("ERROR")
            .setMessage(StringUtils.defaultIfEmpty(status.description, ""))
            .build()

        val responseKey = ProtoUtils.keyForProto(Response.getDefaultInstance())
        metadata.put(responseKey, errorResponse)

        return status
    }

    private class ExceptionHandlingServerCallListener<ReqT, ResT>(
        delegate: ServerCall.Listener<ReqT>,
        private val serverCall: ServerCall<ReqT, ResT>,
        private val metadata: Metadata
    ) : ForwardingServerCallListener.SimpleForwardingServerCallListener<ReqT>(delegate) {

        private val log = LoggerFactory.getLogger(ExceptionHandlingServerCallListener::class.java)

        override fun onHalfClose() {
            try {
                super.onHalfClose()
            } catch (ex: RuntimeException) {
                handleExceptionInternal(ex)
            }
        }

        override fun onReady() {
            try {
                super.onReady()
            } catch (ex: RuntimeException) {
                handleExceptionInternal(ex)
            }
        }

        private fun handleExceptionInternal(ex: RuntimeException) {
            log.error(ex.message, ex)

            val status = when (ex) {
                is IllegalArgumentException -> Status.INVALID_ARGUMENT.withDescription(ex.message)
                is SecurityException -> Status.PERMISSION_DENIED.withDescription(ex.message)
                is StatusRuntimeException -> ex.status
                else -> Status.INTERNAL.withDescription(ex.message)
            }

            serverCall.close(status, metadata)
        }
    }
}
