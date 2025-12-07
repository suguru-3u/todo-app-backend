package com.example.todo_app_backend.controller

import com.example.todo_app_backend.config.BaseIntegrationTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate

@SpringBootTest
class Sample: BaseIntegrationTest() {

    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    @Test
    fun contextLoads() {
        println("Sample test running")
        val count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM user_info", Int::class.java)
        assertThat(count).isEqualTo(0)
    }
}