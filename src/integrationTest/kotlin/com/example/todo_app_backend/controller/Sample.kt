package com.example.todo_app_backend.controller

import com.example.todo_app_backend.config.BaseIntegrationTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.jdbc.core.JdbcTemplate

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Sample : BaseIntegrationTest() {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Test
    fun contextLoads() {
        println("Sample test running")

        val base = Base(restTemplate)  // ← 自分でインスタンス化

        val result = base.contextLoads()

        println("status = ${result?.statusCode}")
        println("headers = ${result?.headers}")
        println("body = ${result?.body}")


        // when
        val headers = HttpHeaders()
        headers.set("Cookie", result!!.headers["Set-Cookie"]!!.joinToString())
        val entity = HttpEntity(null, headers)

        val response = restTemplate.exchange(
            "/api/todos",
            HttpMethod.GET,
            entity,
            Array<TodoController.TodoItem>::class.java
        )

        // then
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        val todos = response.body!!
        println("todos = ${todos.size}")
        assertThat(todos).isEmpty()
    }
}