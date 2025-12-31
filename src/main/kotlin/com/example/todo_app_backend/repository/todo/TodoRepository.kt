package com.example.todo_app_backend.repository.todo

import com.example.todo_app_backend.controller.TodoController
import com.example.todo_app_backend.domain.Todo.RegisterTodo
import com.example.todo_app_backend.domain.Todo.Todo
import com.example.todo_app_backend.domain.User.User

interface TodoRepository {

    fun index(userId: User.AccountId): List<TodoController.TodoItem>

    fun create(todo: RegisterTodo): Int

    fun find(id: Todo.TodoId): List<Todo>

    fun update(todo: Todo)

    fun delete(id: Todo.TodoId)
}