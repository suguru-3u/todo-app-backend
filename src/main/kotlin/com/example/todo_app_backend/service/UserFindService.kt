package com.example.todo_app_backend.service

import com.example.todo_app_backend.model.RegisterUser
import com.example.todo_app_backend.repository.UserRepository
import org.springframework.dao.DataAccessException
import org.springframework.dao.IncorrectResultSizeDataAccessException

class UserFindService(
    private val userRepository: UserRepository
) {
    fun findByByUsername(username: RegisterUser.Username): Result<RegisterUser> {
        try {
            val result = userRepository.findByByUsername(
                username = username
            )
            return Result.success(result!!)
        } catch (e: IncorrectResultSizeDataAccessException) {
            return Result.failure(Error("該当するユーザーが存在しません"))
        } catch (e: DataAccessException) {
            return Result.failure(Error("データベースエラーが発生しました"))
        }
    }
}