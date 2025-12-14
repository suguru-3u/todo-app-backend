package com.example.todo_app_backend.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession
import org.springframework.session.web.http.CookieSerializer
import org.springframework.session.web.http.DefaultCookieSerializer

@Configuration
// このアノテーションがセッションストアをRedisに切り替える「魔法」です
@EnableRedisHttpSession
class SessionConfig {
    //  中身の実装はデフォルトのままでOKですが、必要に応じてカスタマイズも可能

    @Bean
    fun cookieSerializer(): CookieSerializer {
        val serializer = DefaultCookieSerializer()
        serializer.setCookiePath("/")
        serializer.setCookieMaxAge(60 * 30) // 30分
        return serializer
    }
}