package com.example.todo_app_backend.domain.User

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
        }
    }

    @JvmInline
    value class Password(val value: String) {
        init {
            require(value.trim().isNotEmpty()) {
                throw Error("Passwordは空文字で登録できません")
            }
        }
    }

    @JvmInline
    value class HashedPassword private constructor(val value: String) {
        companion object {
            fun create(rowPassword: Password, passwordEncoder: PasswordEncoder): HashedPassword {
                val hashedValue = passwordEncoder.encode(rowPassword.value)
                return HashedPassword(hashedValue)
            }

            fun fromRepository(hashedValue: String): HashedPassword {
                return HashedPassword(hashedValue)
            }
        }
    }
}