package com.example.todo_app_backend.repository

import com.example.todo_app_backend.controller.TodoController
import com.example.todo_app_backend.model.Todo
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.query
import org.springframework.jdbc.core.queryForObject
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

    fun create(todo: Todo): Int {
        val sql = "INSERT INTO todo (text) VALUES (?)"
        return jdbcTemplate.update(sql, todo.text.text)
    }

    fun find(id: Todo.TodoId): List<Todo> {
        val sql = "SELECT * FROM todo WHERE id = ?"
        return jdbcTemplate.query(sql, id.id) { rs, _ ->
            Todo(
                id = Todo.TodoId(rs.getInt("id")),
                text = Todo.TodoText(rs.getString("text")),
                completed = rs.getBoolean("is_completed")
            )
        }

    }

    fun update(todo: Todo) {
        val sql = "UPDATE todo SET is_completed = ? WHERE id = ?"
        jdbcTemplate.update(sql, todo.completed, todo.id.id)
    }

    fun delete(id: Todo.TodoId) {
        val sql = "DELETE FROM todo WHERE id = ?"
        jdbcTemplate.update(sql, id.id)
    }
}