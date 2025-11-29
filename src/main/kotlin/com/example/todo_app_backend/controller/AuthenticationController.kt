package com.example.todo_app_backend.controller

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.context.HttpSessionSecurityContextRepository
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api")
class AuthenticationController(
    val authenticationManager: AuthenticationManager
) {

    @RequestMapping("/login", method = [RequestMethod.POST])
    @ResponseStatus(HttpStatus.OK)
    fun login(@RequestBody loginRequest: LoginRequest, request: HttpServletRequest) {
        // ユーザー名とパスワードを使って認証リクエストを作成
        val authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(
            loginRequest.username, loginRequest.password
        )

        // ログイン情報の認証を行なっている
        val authenticationResponse = authenticationManager.authenticate(authenticationRequest)

        //  セキュリティコンテキストに認証情報を保存
        val context = SecurityContextHolder.createEmptyContext()
        context.authentication = authenticationResponse
        SecurityContextHolder.setContext(context)

        //  SecurityContext をセッションに明示的に保存
        // Spring Session が自動的に処理しますが、明示的に HttpSessionSecurityContextRepository を使用して確実に保存
        // この処理を行わないと、セッションが作成されず、ログイン状態が維持されなかった（通常は自動で保存してくるのだが..原因は不明）
        val securityContextRepository = HttpSessionSecurityContextRepository()
        securityContextRepository.saveContext(context, request, null)
    }

    data class LoginRequest(val username: String, val password: String)
}

