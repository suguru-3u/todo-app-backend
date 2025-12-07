package com.example.todo_app_backend.config

import com.redis.testcontainers.RedisContainer
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Import
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.containers.wait.strategy.Wait
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

        @Container
        @JvmStatic
        val redis =
            RedisContainer("redis:7.2.4").withExposedPorts(6379)
                .waitingFor(Wait.forListeningPort())

        init {
            redis.start()
            System.setProperty("spring.data.redis.host", redis.host)
            System.setProperty("spring.data.redis.port", redis.firstMappedPort.toString())
        }
    }
}
