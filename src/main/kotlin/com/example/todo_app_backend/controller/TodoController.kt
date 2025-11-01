package com.example.todo_app_backend.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api")
class TodoController {

    private val todos = mutableListOf<TodoItem>(
        TodoItem(1, "Buy groceries", false),
        TodoItem(2, "Walk the dog", true),
        TodoItem(3, "Read a book", false)
    )


    @RequestMapping("/todos", method = [RequestMethod.GET])
    fun index(): List<TodoItem> {
        println("GETリクエストを検知")
        return todos
    }

    data class TodoItem(
        val id: Int,
        val text: String,
        val completed: Boolean
    )

    @RequestMapping("/todo", method = [RequestMethod.POST])
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody request: RequestTodoItem) {
        println("POSTリクエストを検知")
        todos.add(
            TodoItem(
                id = todos.size + 1,
                text = request.text,
                completed = request.completed
            )
        )
    }

    data class RequestTodoItem(
        val text: String,
        val completed: Boolean
    )

    @RequestMapping("/todo/{id}", method = [RequestMethod.DELETE])
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int) {
        println("DELETEリクエストを検知")
        todos.removeIf { it.id == id }
    }

    @RequestMapping("/todo/{id}", method = [RequestMethod.PUT])
    @ResponseStatus(HttpStatus.OK)
    fun update(@PathVariable id: Int, @RequestBody request: RequestTodoItem) {
        println("PUTリクエストを検知")
        val index = todos.indexOfFirst { it.id == id }
        if (index != -1) {
            todos[index] = TodoItem(
                id = id,
                text = request.text,
                completed = request.completed
            )
        }
    }
}