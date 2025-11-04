package com.example.todo_app_backend.service

import com.example.todo_app_backend.controller.TodoController
import com.example.todo_app_backend.controller.TodoController.RequestCreateTodoItem
import com.example.todo_app_backend.model.Todo
import com.example.todo_app_backend.repository.TodoRepository
import org.springframework.stereotype.Service

@Service
class TodoService(
    private val todoRepository: TodoRepository
) {

    fun index(): MutableList<TodoController.TodoItem> {
        return todoRepository.index()
    }

    fun create(request: RequestCreateTodoItem) {
        todoRepository.create(
            todo = Todo(
                text = Todo.TodoText(text = request.text)
            )
        )
    }

    fun delete(id: Int) {
        todoRepository.delete(
            id = Todo.TodoId(
                id = id
            )
        )
    }
}