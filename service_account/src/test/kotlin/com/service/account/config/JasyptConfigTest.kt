package com.service.account.config

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import java.util.Base64

class JasyptConfigTest {

    private val log = LoggerFactory.getLogger(JasyptConfigTest::class.java)

    private fun jasyptEncoding(value: String): String {
        val pbeEnc = StandardPBEStringEncryptor()
        pbeEnc.setAlgorithm("PBEWithMD5AndDES")
        pbeEnc.setPassword(System.getenv("JASYPT_KEY"))

        return pbeEnc.encrypt(value)
    }

    private fun jasyptDecoding(value: String): String {
        val pbeEnc = StandardPBEStringEncryptor()
        pbeEnc.setAlgorithm("PBEWithMD5AndDES")
        pbeEnc.setPassword(System.getenv("JASYPT_KEY"))

        return pbeEnc.decrypt(
            value
                .replace("ENC(", "")
                .replace(")", "")
        )
    }

    @Test
    fun `jasypt 비밀키 생성 테스트`(){
        val rawPassword = "test_key"
        val encodedPassword = Base64.getEncoder().encodeToString(rawPassword.toByteArray())

        log.info("encodedPassword: {}", encodedPassword)
    }

    @Test
    fun `jasypt 인코딩 테스트`(){
        val username = "admin"
        val password = "admin"

        log.info("username: {}", jasyptEncoding(username))
        log.info("password: {}", jasyptEncoding(password))
    }

    @Test
    fun `jasypt 디코딩 테스트`(){
        val username = "ENC(0quG6FVbayjtAMT/26YQxA==)"
        val password = "ENC(cOh6l6GKbZQdo8p9EE98/A==)"

        log.info("username: {}", jasyptDecoding(username))
        log.info("password: {}", jasyptDecoding(password))
    }
}
