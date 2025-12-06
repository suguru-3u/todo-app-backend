package com.example.todo_app_backend.repository

import com.example.todo_app_backend.controller.TodoController
import com.example.todo_app_backend.model.RegisterTodo
import com.example.todo_app_backend.model.Todo
import com.example.todo_app_backend.model.User
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.query
import org.springframework.stereotype.Repository

@Repository
class TodoRepositoryIml(
    private val jdbcTemplate: JdbcTemplate,
) : TodoRepository {

    override fun index(userId: User.AccountId): List<TodoController.TodoItem> {
        val sql = "SELECT * FROM todo WHERE accountId = ?"
        return jdbcTemplate.query(sql, userId.id) { rs, _ ->
            TodoController.TodoItem(
                id = rs.getInt("id"),
                text = rs.getString("text"),
                completed = rs.getBoolean("is_completed")
            )
        }
    }

    override fun create(todo: RegisterTodo): Int {
        val sql = "INSERT INTO todo (text,user_id) VALUES (?,?)"
        return jdbcTemplate.update(sql, todo.text.text, todo.userId)
    }

    override fun find(id: Todo.TodoId): List<Todo> {
        val sql = "SELECT * FROM todo WHERE id = ?"
        return jdbcTemplate.query(sql, id.id) { rs, _ ->
            Todo(
                id = Todo.TodoId(rs.getInt("id")),
                text = Todo.TodoText(rs.getString("text")),
                completed = rs.getBoolean("is_completed")
            )
        }

    }

    override fun update(todo: Todo) {
        val sql = "UPDATE todo SET is_completed = ? WHERE id = ?"
        jdbcTemplate.update(sql, todo.completed, todo.id.id)
    }

    override fun delete(id: Todo.TodoId) {
        val sql = "DELETE FROM todo WHERE id = ?"
        jdbcTemplate.update(sql, id.id)
    }
}