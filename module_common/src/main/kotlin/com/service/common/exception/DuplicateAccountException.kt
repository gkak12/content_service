package com.service.common.exception

class DuplicateAccountException: RuntimeException {

    override val message: String

    constructor(): super() {
        this.message = "계정이 중복됩니다."
    }

    constructor(message: String): super() {
        this.message = message
    }
}