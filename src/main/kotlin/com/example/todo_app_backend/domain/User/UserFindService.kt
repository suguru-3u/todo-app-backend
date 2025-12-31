package com.example.todo_app_backend.domain.User

import com.example.todo_app_backend.repository.user.UserRepository
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
            println("ログの出力")
            return Result.failure(Error("該当するユーザーが存在しません"))
        } catch (e: DataAccessException) {
            return Result.failure(Error("データベースエラーが発生しました"))
        }
    }
}