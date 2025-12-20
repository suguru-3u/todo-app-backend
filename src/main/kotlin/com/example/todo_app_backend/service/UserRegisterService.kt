package com.example.todo_app_backend.service

import com.example.todo_app_backend.model.RegisterUser
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserRegisterService(
    val passwordEncoder: PasswordEncoder
) {

    fun execute(username: RegisterUser.Username, password: RegisterUser.Password) {
        println("ユーザー登録サービスが呼び出されました")

        val hashedPassword = RegisterUser.HashedPassword.create(
            rowPassword = password,
            passwordEncoder = passwordEncoder
        )

        val registerUser = RegisterUser(
            username = username,
            password = hashedPassword
        )
        // ユーザーのエンティティを作成する
        // データベースにusernameが存在するか確認
        // パスワードを暗号化する
    }
}