package com.example.todo_app_backend.controller

import com.example.todo_app_backend.config.BaseIntegrationTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.ResponseEntity

class Base(
    private val restTemplate: TestRestTemplate
): BaseIntegrationTest() {

    fun contextLoads(): ResponseEntity<String>? {
        val loginResponse = restTemplate.postForEntity(
            "/api/login",
            mapOf("username" to "user", "password" to "password"),
            String::class.java
        )
        return loginResponse
    }
}