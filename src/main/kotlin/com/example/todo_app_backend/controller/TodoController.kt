package com.example.todo_app_backend.controller

import com.example.todo_app_backend.service.TodoService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api")
class TodoController(
    private val todoService: TodoService
) {

    private val todos = mutableListOf<TodoItem>(
        TodoItem(1, "Buy groceries", false),
        TodoItem(2, "Walk the dog", true),
        TodoItem(3, "Read a book", false)
    )


    @RequestMapping("/todos", method = [RequestMethod.GET])
    fun index(): List<TodoItem> {
        println("GETリクエストを検知")
        return todoService.index()
    }

    data class TodoItem(
        val id: Int,
        val text: String,
        val completed: Boolean
    )

    @RequestMapping("/todo", method = [RequestMethod.POST])
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody request: RequestCreateTodoItem) {
        println("POSTリクエストを検知")
        todoService.create(request)
    }

    data class RequestTodoItem(
        val text: String,
        val completed: Boolean
    )

    @RequestMapping("/todo/{id}", method = [RequestMethod.DELETE])
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int) {
        println("DELETEリクエストを検知")
        todoService.delete(id)
    }

    @RequestMapping("/todo/{id}", method = [RequestMethod.PUT])
    @ResponseStatus(HttpStatus.OK)
    fun update(@PathVariable id: Int, @RequestBody request: RequestUpdateTodoItem) {
        println("PUTリクエストを検知")
        todoService.update(id, request)
    }

    data class RequestCreateTodoItem(
        val text: String
    )

    data class RequestUpdateTodoItem(
        val text: String,
        val completed: Boolean
    )
}