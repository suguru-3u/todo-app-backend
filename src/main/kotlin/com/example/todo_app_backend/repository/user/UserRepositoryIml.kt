package com.example.todo_app_backend.repository.user

import com.example.todo_app_backend.domain.User.RegisterUser
import org.springframework.jdbc.core.JdbcTemplate

class UserRepositoryIml(
    private val jdbcTemplate: JdbcTemplate
) : UserRepository {

    override fun findByByUsername(username: RegisterUser.Username): RegisterUser? {
        val sql = "SELECT * FROM user_info WHERE accountId = ?"
        val user = jdbcTemplate.queryForObject(
            sql,
            { rs, _ ->
                RegisterUser(
                    username = RegisterUser.Username(rs.getString("accountId")),
                    password = RegisterUser.HashedPassword.fromRepository(rs.getString("password"))
                )
            },
            username.value
        )
        return user
    }

    override fun registerUser(registerUser: RegisterUser): Int {
        val sql = "INSERT INTO user_info (accountId, password) VALUES (?,?)"
        return jdbcTemplate.update(sql, registerUser.username.value, registerUser.password.value)
    }
}