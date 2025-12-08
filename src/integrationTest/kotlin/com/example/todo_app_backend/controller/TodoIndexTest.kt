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
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TodoIndexTest : BaseIntegrationTest() {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    @BeforeTest
    fun setUp() {
        val sql = "INSERT INTO todo (text, accountId) VALUES ('Todo Index IntegrationTest', 'user')"
        jdbcTemplate.execute(sql)
    }

    @AfterTest
    fun cleanup() {
        val sql = "DELETE FROM todo WHERE text = 'Todo Index IntegrationTest'"
        jdbcTemplate.execute(sql)
    }

    @Test
    fun contextLoads() {
        // ログインAPIの実行
        val base = Base(restTemplate)
        val result = base.contextLoads()

        // セッションの取得
        val headers = HttpHeaders()
        headers.set("Cookie", result!!.headers["Set-Cookie"]!!.joinToString())
        val entity = HttpEntity(null, headers)

        // リクエストの生成&実行
        val response = restTemplate.exchange(
            "/api/todos",
            HttpMethod.GET,
            entity,
            Array<TodoController.TodoItem>::class.java
        )

        // 検証
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        val todos = response.body!!
        assertThat(todos).hasSize(1)
        assertThat(todos[0].text).isEqualTo("Todo Index IntegrationTest")
    }
}