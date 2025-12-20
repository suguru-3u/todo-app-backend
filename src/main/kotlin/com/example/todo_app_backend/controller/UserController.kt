package com.example.todo_app_backend.controller

import com.example.todo_app_backend.model.RegisterUser
import com.example.todo_app_backend.service.UserRegisterService
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 以下のAPIを作成する
 * ユーザーの新規登録
 * ユーザー情報の取得
 */

@RestController
@RequestMapping("api")
class UserController(
    private val userRegisterService: UserRegisterService
) {

    @RequestMapping("/users/register")
    fun register(@RequestBody request: RequestRegisterUser) {
        println("ユーザー登録APIリクエストを検知")
        userRegisterService.execute(
            username = RegisterUser.Username(request.username),
            password = RegisterUser.Password(request.password)
        )
    }

    data class RequestRegisterUser(
        val username: String,
        val password: String
    )
}