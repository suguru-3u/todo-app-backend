package com.example.todo_app_backend.service

import org.springframework.stereotype.Service

@Service
class UserRegisterService {

    fun execute(username: String, password: String) {
        println("ユーザー登録サービスが呼び出されました")
        // ここにユーザー登録のロジックを実装する

        // 引数のusernameとpasswordを値オブジェクトに変更する
        // ユーザーのエンティティを作成する
        // データベースにusernameが存在するか確認
        // パスワードを暗号化する
    }
}