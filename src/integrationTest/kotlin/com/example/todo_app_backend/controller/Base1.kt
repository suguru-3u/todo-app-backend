package com.example.todo_app_backend.controller

import com.example.todo_app_backend.config.BaseIntegrationTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity

class Base1(
    private val restTemplate: TestRestTemplate
): BaseIntegrationTest() {

        fun getHttpSession(): HttpEntity<Nothing> {
            val headers = HttpHeaders()
            val result = contextLoads()
            headers.set("Cookie", result!!.headers["Set-Cookie"]!!.joinToString())
            return HttpEntity(null, headers)
        }

        private fun contextLoads(): ResponseEntity<String>? {
            val loginResponse = restTemplate.postForEntity(
                "/api/login",
                mapOf("username" to "user", "password" to "password"),
                String::class.java
            )
            return loginResponse
        }
}