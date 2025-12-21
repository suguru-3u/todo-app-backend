package com.example.todo_app_backend.service

import com.example.todo_app_backend.model.RegisterUser
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserRegisterService(
    private val passwordEncoder: PasswordEncoder,
    private val userFindService: UserFindService
) {

    fun execute(username: RegisterUser.Username, password: RegisterUser.Password) {
        println("ユーザー登録サービスが呼び出されました")

        userFindService.findByByUsername(username).onSuccess {
            throw Error("既に登録されているユーザーです")
        }

        val hashedPassword = RegisterUser.HashedPassword.create(
            rowPassword = password,
            passwordEncoder = passwordEncoder
        )

        val registerUser = RegisterUser(
            username = username,
            password = hashedPassword
        )

        // ユーザーを登録する
    }
}