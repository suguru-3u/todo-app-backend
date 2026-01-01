package com.example.todo_app_backend.usecase.user

import com.example.todo_app_backend.domain.User.RegisterUser
import com.example.todo_app_backend.domain.User.UserFindService
import com.example.todo_app_backend.repository.user.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserRegisterUseCase(
    private val passwordEncoder: PasswordEncoder,
    private val userFindService: UserFindService,
    private val userRepository: UserRepository
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

        userRepository.registerUser(
            registerUser = RegisterUser(
                username = username,
                password = hashedPassword
            )
        )
    }
}