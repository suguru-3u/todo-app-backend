package com.example.todo_app_backend.config

import org.springframework.context.annotation.Configuration
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession

@Configuration
// このアノテーションがセッションストアをRedisに切り替える「魔法」です
@EnableRedisHttpSession
class SessionConfig {
    //  中身の実装はデフォルトのままでOKですが、必要に応じてカスタマイズも可能
}