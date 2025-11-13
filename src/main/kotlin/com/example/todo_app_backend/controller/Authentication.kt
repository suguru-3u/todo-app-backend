package com.example.todo_app_backend.controller

import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api")
class Authentication(
    val authenticationManager: AuthenticationManager
) {

    @RequestMapping("/login", method = [RequestMethod.POST])
    fun login(@RequestBody loginRequest: LoginRequest) {
        val authenticationRequest =
            UsernamePasswordAuthenticationToken.unauthenticated(
                loginRequest.username, loginRequest.password
            )
        val authenticationResponse =
            authenticationManager.authenticate(authenticationRequest)
        println("認証結果: $authenticationResponse")
    }

    data class LoginRequest(val username: String, val password: String)

}