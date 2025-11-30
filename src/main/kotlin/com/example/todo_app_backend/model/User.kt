package com.example.todo_app_backend.model

class User(
    val userId: AccountId,
) {
    @JvmInline
    value class AccountId(val id: String)
}