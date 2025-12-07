package com.example.todo_app_backend.config

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.core.io.ClassPathResource
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator
import javax.sql.DataSource

@TestConfiguration
class IntegrationTestConfig {

    @Bean
    fun initSchema(dataSource: DataSource): ResourceDatabasePopulator {
        val populator = ResourceDatabasePopulator()
        populator.addScript(ClassPathResource("schema.sql"))
        populator.execute(dataSource)
        return populator
    }
}

