package com.example.todo_app_backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TodoAppBackendApplication

fun main(args: Array<String>) {
	runApplication<TodoAppBackendApplication>(*args)
}
