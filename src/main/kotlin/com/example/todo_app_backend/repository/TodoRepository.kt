package com.example.todo_app_backend.repository

import com.example.todo_app_backend.controller.TodoController
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class TodoRepository(
    private val jdbcTemplate: JdbcTemplate,
) {

    fun index(): MutableList<TodoController.TodoItem> {
        val sql = "SELECT * FROM todo"
        return jdbcTemplate.query(sql) { rs, _ ->
            TodoController.TodoItem(
                id = rs.getInt("id"),
                text = rs.getString("text"),
                completed = rs.getBoolean("is_completed")
            )
        }
    }
}