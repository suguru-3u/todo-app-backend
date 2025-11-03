package com.example.todo_app_backend.model

class Todo(
    val id: TodoId = TodoId(0),
    val text: TodoText,
    val completed: Boolean = false,
) {
    @JvmInline
    value class TodoId(val id: Int)

    @JvmInline
    value class TodoText(val text: String) {
        init {
            println("文字の確認: $text")
            println("文字の確認: ${text.trim()}")
            println("文字の確認: ${text.trim().isEmpty()}")
            require(text.trim().isNotEmpty()) {
                throw Error("Todoは空文字で登録できません")
            }
            require(text.length <= 256) {
                throw Error("Todoの文字数が256文字よりも大きくいです")
            }
        }
    }
}

