package com.example.todo_app_backend.repository

import com.example.todo_app_backend.model.RegisterUser

interface UserRepository {

    fun findByByUsername(username: RegisterUser.Username): RegisterUser?

    fun registerUser(registerUser: RegisterUser): Int
}