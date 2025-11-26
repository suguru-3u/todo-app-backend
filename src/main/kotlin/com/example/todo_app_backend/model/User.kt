package com.example.todo_app_backend.model

class User(
    val userId: UserId,
) {
    @JvmInline
    value class UserId(val id: Int)
}