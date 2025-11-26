package com.example.todo_app_backend.service

import com.example.todo_app_backend.controller.TodoController
import com.example.todo_app_backend.controller.TodoController.RequestCreateTodoItem
import com.example.todo_app_backend.model.RegisterTodo
import com.example.todo_app_backend.model.Todo
import com.example.todo_app_backend.repository.TodoRepository
import org.springframework.stereotype.Service
import java.security.Principal

@Service
class TodoService(
    private val todoRepository: TodoRepository
) {

    fun index(): MutableList<TodoController.TodoItem> {
        return todoRepository.index()
    }

    fun create(principal: Principal, request: RequestCreateTodoItem) {
        todoRepository.create(
            todo = RegisterTodo(
                text = RegisterTodo.TodoText(text = request.text),
                userId = principal.name.toInt()
            )
        )
    }

    fun update(id: Int, request: TodoController.RequestUpdateTodoItem) {
        val todoId = Todo.TodoId(id = id)
        val result = todoRepository.find(id = todoId)
        if (result.isEmpty()) throw Error("存在しないTodoIdです")
        todoRepository.update(
            todo = Todo(
                id = todoId,
                text = Todo.TodoText(text = request.text),
                completed = request.completed
            )
        )
    }

    fun delete(id: Int) {
        val todoId = Todo.TodoId(id = id)
        val result = todoRepository.find(id = todoId)
        if (result.isEmpty()) throw Error("存在しないTodoIdです")
        todoRepository.delete(
            id = todoId
        )
    }
}