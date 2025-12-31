package com.example.todo_app_backend.repository.user

import com.example.todo_app_backend.domain.User.RegisterUser

interface UserRepository {

    fun findByByUsername(username: RegisterUser.Username): RegisterUser?

    fun registerUser(registerUser: RegisterUser): Int
}