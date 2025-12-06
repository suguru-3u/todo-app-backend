package com.example.todo_app_backend.repository

import com.example.todo_app_backend.controller.TodoController
import com.example.todo_app_backend.model.RegisterTodo
import com.example.todo_app_backend.model.Todo
import com.example.todo_app_backend.model.User

interface TodoRepository {

    fun index(userId: User.AccountId): List<TodoController.TodoItem>

    fun create(todo: RegisterTodo): Int

    fun find(id: Todo.TodoId): List<Todo>

    fun update(todo: Todo)

    fun delete(id: Todo.TodoId)
}