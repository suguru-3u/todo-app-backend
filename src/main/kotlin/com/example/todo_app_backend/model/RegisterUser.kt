package com.example.todo_app_backend.model

import org.springframework.security.crypto.password.PasswordEncoder

class RegisterUser(
    val username: Username,
    val password: HashedPassword
) {
    @JvmInline
    value class Username(val value: String) {
        init {
            require(value.trim().isNotEmpty()) {
                throw Error("Usernameは空文字で登録できません")
            }
//            require(username.length <= 32) {
//                throw Error("Usernameの文字数が32文字よりも大きくいです")
//            }
        }
    }

    @JvmInline
    value class Password(val value: String) {
        init {
            require(value.trim().isNotEmpty()) {
                throw Error("Passwordは空文字で登録できません")
            }
//            require(password.length <= 64) {
//                throw Error("Passwordの文字数が64文字よりも大きくいです")
//            }
        }
    }

    @JvmInline
    value class HashedPassword private constructor(val value: String) {
        companion object {
            fun create(rowPassword: Password, passwordEncoder: PasswordEncoder): HashedPassword {
                val hashedValue = passwordEncoder.encode(rowPassword.value)
                return HashedPassword(hashedValue)
            }
        }
    }
}