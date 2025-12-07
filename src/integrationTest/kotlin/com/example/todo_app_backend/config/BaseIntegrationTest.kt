package com.example.todo_app_backend.config

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Import
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@SpringBootTest
@Import(IntegrationTestConfig::class)
abstract class BaseIntegrationTest {

    companion object {
        @Container
        @ServiceConnection
        val mysql = MySQLContainer<Nothing>("mysql:8.0")
    }
}
